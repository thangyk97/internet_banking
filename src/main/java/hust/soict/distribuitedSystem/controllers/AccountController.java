package hust.soict.distribuitedSystem.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hust.soict.distribuitedSystem.entities.Message;
import hust.soict.distribuitedSystem.entities.User;

@Controller
public class AccountController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/room")
	public void greeting(Principal principal, @Payload User user) throws  Exception {
		JsonObject response = new JsonObject();
		response.addProperty("title", "role");
		
		Gson gson = new GsonBuilder().create();
		JsonElement element = gson.toJsonTree(user,user.getClass());
		response.add("content", element);
		
		messagingTemplate.convertAndSendToUser(user.getUsername(),
												"/queue/reply",
												gson.toJson(response));
	}

}