package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonObject;

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
		User user1 = users.get(0);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("username", user1.getUsername());
		jsonObject.addProperty("role", user1.getRole());
		jsonObject.addProperty("first_name", user1.getFirstName());
		jsonObject.addProperty("last_name", user1.getLastName());
		if (user1.getRole() != 2) {
			jsonObject.addProperty("ac_no", user1.getAccount().getAc_no());	
		}
		jsonObject.addProperty("open_date", user1.getStartTime());
		jsonObject.addProperty("gender", user1.getGender());
		
		String jsonString = Utils.creatResponseJson("role", jsonObject);
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												jsonString);
	}
}
