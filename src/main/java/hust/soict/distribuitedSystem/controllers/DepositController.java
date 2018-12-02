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
import hust.soict.distribuitedSystem.entities.Deposit;
import hust.soict.distribuitedSystem.entities.User;
import hust.soict.distribuitedSystem.repositories.AccountRepository;
import hust.soict.distribuitedSystem.repositories.DepositRepository;
import hust.soict.distribuitedSystem.repositories.UserRepository;

@Controller
public class DepositController {
	@Autowired
	private DepositRepository depositRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/deposit")
	public void deposit(Principal principal, 
						@Payload String message,
						StompHeaderAccessor accessor) {
		
		JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
		String username = jsonObject.get("username").getAsString();
		Float amount = jsonObject.get("amount").getAsFloat();
	
		User user = userRepository.getByUsername(username).get(0);
		Account account = user.getAccount();
		
		Deposit deposit = new Deposit();
		deposit.setAmount(amount);
		deposit.setCust_id(user.getId());
		deposit.setAc_no(account.getAc_no());
		deposit.setStatus(0);
		
		account.setBalance(account.getBalance() + deposit.getAmount());
		accountRepository.save(account);

		deposit.setStatus(1);
		depositRepository.save(deposit);
		
		String response = Utils.creatResponseJson("deposit", "pass");
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
				"/queue/reply",
				response);
	}
}
