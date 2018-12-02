package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hust.soict.distribuitedSystem.Utils;
import hust.soict.distribuitedSystem.entities.Account;
import hust.soict.distribuitedSystem.entities.User;
import hust.soict.distribuitedSystem.entities.Withdraw;
import hust.soict.distribuitedSystem.repositories.AccountRepository;
import hust.soict.distribuitedSystem.repositories.UserRepository;
import hust.soict.distribuitedSystem.repositories.WithdrawRepository;

@Controller
public class WithdrawController {
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/withdraw")
	public void addWithdraw(Principal principal, 
							@Payload String message,
							StompHeaderAccessor accessor) {

		JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
		String response = "";
		User user = userRepository.getByUsername(
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
			withdrawRepository.save(withdraw);
			
			
			if (account.getBalance() - withdraw.getAmount() > 0) {
				account.setBalance(account.getBalance() - withdraw.getAmount());
				accountRepository.save(account);
				
				withdraw.setStatus(1);
				withdrawRepository.save(withdraw);
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
