package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import hust.soict.distribuitedSystem.Utils;
import hust.soict.distribuitedSystem.entities.User;
import hust.soict.distribuitedSystem.repositories.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/findRole")
	public void findRole(Principal principal,
						@Payload User user,
						StompHeaderAccessor accessor) throws  Exception {

		List<User> users =  userRepository.fetchUserBy(user.getUsername(), user.getPassword());

		String jsonString = "";
		jsonString = Utils.creatResponseJson("role", users.get(0));
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												jsonString);
	}
}
