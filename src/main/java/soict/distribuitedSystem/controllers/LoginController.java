package soict.distribuitedSystem.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.google.gson.JsonObject;

import soict.distribuitedSystem.Utils;
import soict.distribuitedSystem.entities.Account;
import soict.distribuitedSystem.entities.User;
import soict.distribuitedSystem.repositoriesA.AccountRepositoryA;
import soict.distribuitedSystem.repositoriesA.UserRepositoryA;

@Controller
public class LoginController {
	@Autowired
	private UserRepositoryA userRepository;
	@Autowired
	private AccountRepositoryA accountRepositoryA;
	
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
		jsonObject.addProperty("birthday", user1.getBirthday());
		jsonObject.addProperty("address", user1.getAddress());
		
		if (user1.getRole() != 2) {
			jsonObject.addProperty("ac_no", user1.getAccount().getAc_no());	
			
			Account account = user1.getAccount();
			int flag = account.getFlag();
			account.setFlag(flag==1?0:1);
			accountRepositoryA.save(account);
			user1.setFlag(flag);
			userRepository.save(user1);
		}
		jsonObject.addProperty("open_date", user1.getStartTime());
		jsonObject.addProperty("gender", user1.getGender());
		
		
		String jsonString = Utils.creatResponseJson("role", jsonObject);
		System.out.println(accessor);
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												jsonString);
	}
}
