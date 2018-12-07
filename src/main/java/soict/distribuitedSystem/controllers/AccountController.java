package soict.distribuitedSystem.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import soict.distribuitedSystem.Utils;
import soict.distribuitedSystem.entities.Account;
import soict.distribuitedSystem.entities.User;
import soict.distribuitedSystem.repositoriesA.AccountRepositoryA;
import soict.distribuitedSystem.repositoriesA.UserRepositoryA;

@Controller
public class AccountController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private AccountRepositoryA accountRepository;
	@Autowired
	private UserRepositoryA userRepository;
	
	@MessageMapping("/getInforAccount")
	public void getInforAccount(Principal principal, 
			@Payload String message,
			StompHeaderAccessor accessor) {
		
		String username = Utils.getUserPrincipal(accessor);
		User user = userRepository.getByUsername(username).get(0);
		Account account = user.getAccount();
		account.setUsers(null);
		
		String response = Utils.creatResponseJson("inforAccount", account);
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
				"/queue/reply",
				response);
	}

	@MessageMapping("/addAccount")
	public void addUser(Principal principal, 
			@Payload Account account,
			StompHeaderAccessor accessor) throws  Exception {
		
		accountRepository.save(account);

		String response = Utils.creatResponseJson("addAccountResponse",
													new String("oke"));
		
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												response);
	}

}