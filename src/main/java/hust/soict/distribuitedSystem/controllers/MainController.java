package hust.soict.distribuitedSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import hust.soict.distribuitedSystem.entities.Account;
import hust.soict.distribuitedSystem.repositories.UserRepository;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Boolean greeting(String str) throws Exception {
		
		JsonObject jObj = new Gson().fromJson(str, JsonObject.class);
		Account account = new Account(jObj.get("cus_id").getAsInt(),
										jObj.get("password").toString(),
										jObj.get("balance").getAsDouble(),
										jObj.get("openDate").toString());
		userRepository.save(account);
		
		return true;
	}
}