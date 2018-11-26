package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Constants;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hust.soict.distribuitedSystem.Utils;
import hust.soict.distribuitedSystem.entities.User;
import hust.soict.distribuitedSystem.repositories.UserRepository;

@Controller
@Scope("session")
public class LoginController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/room")
	public void findRole(HttpSession session, Principal principal, @Payload User user) throws  Exception {

		List<User> users =  userRepository.fetchUserBy(user.getUsername(), user.getPassword());

		String jsonString = "";
		jsonString = Utils.creatResponseJson("role", users.get(0));
		
		messagingTemplate.convertAndSendToUser(user.getUsername(),
												"/queue/reply",
												jsonString);
	}
}
