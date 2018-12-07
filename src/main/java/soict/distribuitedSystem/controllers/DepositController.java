package soict.distribuitedSystem.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import soict.distribuitedSystem.Utils;
import soict.distribuitedSystem.entities.Account;
import soict.distribuitedSystem.entities.Deposit;
import soict.distribuitedSystem.entities.User;
import soict.distribuitedSystem.repositoriesA.AccountRepositoryA;
import soict.distribuitedSystem.repositoriesA.DepositRepositoryA;
import soict.distribuitedSystem.repositoriesA.UserRepositoryA;

@Controller
public class DepositController {
	@Autowired
	private DepositRepositoryA depositRepositoryA;
	@Autowired
	private UserRepositoryA userRepositoryA;
	@Autowired
	private AccountRepositoryA accountRepositoryA;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/deposit")
	public void deposit(Principal principal, 
						@Payload String message,
						StompHeaderAccessor accessor) {
		String response = "";
		
		// Parse json message
		JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
		String username = jsonObject.get("username").getAsString();
		int ac_no = jsonObject.get("ac_no").getAsInt();
		Float amount = jsonObject.get("amount").getAsFloat();
		
		try {
			// Get user and account by username and ac_no
			User user = userRepositoryA.getByUsername(username).get(0);
			System.out.println(user);
			Optional<Account> temp_account = accountRepositoryA.findById(ac_no);
			Account account = temp_account.get();
			
			// Check account of user
			if (user.getAccount().getAc_no() != account.getAc_no()) {
				response = Utils.creatResponseJson("deposit", new String("fail"));
			} else {
				Deposit deposit = new Deposit();
				deposit.setAmount(amount);
				deposit.setCust_id(user.getId());
				deposit.setAc_no(account.getAc_no());
				deposit.setStatus(0);
				deposit.setOpenDate(LocalDate.now().toString());
				
				account.setBalance(account.getBalance() + deposit.getAmount());
				accountRepositoryA.save(account);

				deposit.setStatus(1);
				deposit.setCloseDate(LocalDate.now().toString());
				depositRepositoryA.save(deposit);
				
				response = Utils.creatResponseJson("deposit", new String("pass"));
			}
		} catch (Exception e) {
			response = Utils.creatResponseJson("deposit", new String("fail"));
		}
		
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
				"/queue/reply",
				response);
	}
}
