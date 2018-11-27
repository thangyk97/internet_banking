package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import hust.soict.distribuitedSystem.Utils;
import hust.soict.distribuitedSystem.entities.Account;
import hust.soict.distribuitedSystem.repositories.AccountRepository;

@Controller
public class AccountController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private AccountRepository accountRepository;

	@MessageMapping("/addAccount")
	public void addUser(Principal principal, 
			@Payload Account account,
			StompHeaderAccessor accessor) throws  Exception {
		
		System.out.println(account.getOpenDate());
		
		accountRepository.save(account);

		String response = Utils.creatResponseJson("addAccountResponse",
													new String("oke"));
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												response);
	}

}