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
import hust.soict.distribuitedSystem.entities.User;
import hust.soict.distribuitedSystem.repositories.UserRepository;

@Controller
public class UserController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private UserRepository userRepository;
	
	@MessageMapping("/addUser")
	public void addUser(Principal principal,
						@Payload User user, @Payload Account account,
						StompHeaderAccessor accessor) throws  Exception {
		
		System.out.println(user);
		System.out.println(account);
		
//		userRepository.save(user);
		
		String response = Utils.creatResponseJson("addUserResponse", new String("oke"));
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												response);
	}
}
