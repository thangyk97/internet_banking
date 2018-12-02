package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import ch.qos.logback.classic.pattern.Util;
import hust.soict.distribuitedSystem.Utils;
import hust.soict.distribuitedSystem.entities.Account;
import hust.soict.distribuitedSystem.entities.User;
import hust.soict.distribuitedSystem.repositories.AccountRepository;
import hust.soict.distribuitedSystem.repositories.UserRepository;

@Controller
public class AccountController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserRepository userRepository;
	
	@MessageMapping("/getInforAccount")
	public void getInforAccount(Principal principal, 
			@Payload String message,
			StompHeaderAccessor accessor) {
		
		String username = Utils.getUserPrincipal(accessor);
		User user = userRepository.getByUsername(username).get(0);
		Account account = user.getAccount();
		account.setUsers(null);
		
		String response = Utils.creatResponseJson("inforAccount", account);
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
				"/queue/reply",
				response);
	}

	@MessageMapping("/addAccount")
	public void addUser(Principal principal, 
			@Payload Account account,
			StompHeaderAccessor accessor) throws  Exception {
		
		accountRepository.save(account);

		String response = Utils.creatResponseJson("addAccountResponse",
													new String("oke"));
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												response);
	}

}