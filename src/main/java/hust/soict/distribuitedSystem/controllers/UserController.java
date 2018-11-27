package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import hust.soict.distribuitedSystem.Utils;
import hust.soict.distribuitedSystem.entities.Account;
import hust.soict.distribuitedSystem.entities.User;
import hust.soict.distribuitedSystem.repositories.AccountRepository;
import hust.soict.distribuitedSystem.repositories.UserRepository;

@Controller
public class UserController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	@MessageMapping("/addUser")
	public void addUser(Principal principal,
						@Payload User user, @Payload Account account,
						StompHeaderAccessor accessor) throws  Exception {
		
		Optional<Account> a = accountRepository.findById(account.getAc_no());
		user.setAccount(a.get());
		userRepository.save(user);
		
		String response = Utils.creatResponseJson("addUserResponse", new String("oke"));
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												response);
	}
}
