package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
	public void addUser(Principal principal, @Payload Account account) throws  Exception {
		accountRepository.save(account);
		
		String response = Utils.creatResponseJson("addAccountResponse", null);
		
		messagingTemplate.convertAndSendToUser("username", "/queue/reply", response);
	}

}