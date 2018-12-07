package soict.distribuitedSystem.controllers;

import java.security.Principal;
import java.time.LocalDate;

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
import soict.distribuitedSystem.entities.User;
import soict.distribuitedSystem.entities.Withdraw;
import soict.distribuitedSystem.repositoriesA.AccountRepositoryA;
import soict.distribuitedSystem.repositoriesA.UserRepositoryA;
import soict.distribuitedSystem.repositoriesA.WithdrawRepositoryA;
import soict.distribuitedSystem.repositoriesB.WithdrawRepositoryB;

@Controller
public class WithdrawController {
	@Autowired
	private WithdrawRepositoryA withdrawRepositoryA;
	@Autowired
	private WithdrawRepositoryB withdrawRepositoryB;
	
	@Autowired
	private UserRepositoryA userRepositoryA;
	@Autowired
	private AccountRepositoryA accountRepositoryA;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/withdraw")
	public void addWithdraw(Principal principal, 
							@Payload String message,
							StompHeaderAccessor accessor) {

		JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
		String response = "";
		User user = userRepositoryA.getByUsername(
									Utils.getUserPrincipal(accessor)).get(0);
		

		if (! user.getPassword().equals(jsonObject.get("password").getAsString())) {
			response = Utils.creatResponseJson("withdraw", "pass_wrong");
		} else {
			Account account = user.getAccount();
			
			Withdraw withdraw = new Withdraw();
			withdraw.setAmount(jsonObject.get("amount").getAsDouble());
			withdraw.setAc_no(account.getAc_no());
			withdraw.setCus_id(user.getId());
			withdraw.setStatus(0);
			withdraw.setOpenDate(LocalDate.now().toString());
			if (user.getFlag() == 0) {
				withdrawRepositoryA.save(withdraw);
			} else {
				withdrawRepositoryB.save(withdraw);
			}
			
			
			
			if (account.getBalance() - withdraw.getAmount() > 0) {
				account.setBalance(account.getBalance() - withdraw.getAmount());
				accountRepositoryA.save(account);
				
				withdraw.setStatus(1);
				withdraw.setCloseDate(LocalDate.now().toString());
				withdraw.setUpdatedAt(LocalDate.now().toString());
				
				if (user.getFlag() == 0) {
					withdrawRepositoryA.save(withdraw);
				} else {
					withdrawRepositoryB.save(withdraw);
				}
				response = Utils.creatResponseJson("withdraw", "pass");
			} else {
				response = Utils.creatResponseJson("withdraw",
						"not_enough_money");
			}
		}

		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
				"/queue/reply",
				response);
	}
}
