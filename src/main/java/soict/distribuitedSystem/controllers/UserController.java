package soict.distribuitedSystem.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
public class UserController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@Autowired
	private UserRepositoryA userRepositoryA;
	@Autowired
	private AccountRepositoryA accountRepositoryA;
	
	@MessageMapping("/addUser")
	public void addUser(Principal principal,
						@Payload User user, @Payload Account account,
						StompHeaderAccessor accessor) throws  Exception {
		String response;
		List<User> users = userRepositoryA.getByUsername(user.getUsername());
		if (users.isEmpty()) {
			Optional<Account> a = accountRepositoryA.findById(account.getAc_no());
			if (a.isPresent()) {
				user.setAccount(a.get());
				user.setRole(1);
			} else {
				account.setFlag(0);
				account.setOpenDate(LocalDate.now().toString());
				accountRepositoryA.save(account);
				user.setAccount(account);
			}
			user.setStartTime(LocalDate.now().toString());
			userRepositoryA.save(user);
			
			response = Utils.creatResponseJson("addUserResponse", new String("oke"));
		} else {
			response = Utils.creatResponseJson("addUserResponse", new String("fail"));
		}
			
			
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												response);
	}
	
	@MessageMapping("/addUser2Account")
	public void addUser2Account(Principal principal,
						@Payload User user, @Payload Account account,
						StompHeaderAccessor accessor) throws  Exception {
		String response;
		List<User> users = userRepositoryA.getByUsername(user.getUsername());
		if (users.isEmpty()) {
			Optional<Account> a = accountRepositoryA.findById(account.getAc_no());
			if (a.isPresent()) {
				user.setAccount(a.get());
				user.setRole(1);
			} else {
				account.setFlag(0);
				account.setOpenDate(LocalDate.now().toString());
				accountRepositoryA.save(account);
				user.setAccount(account);
			}
			user.setStartTime(LocalDate.now().toString());
			userRepositoryA.save(user);
			
			response = Utils.creatResponseJson("addUser2Account", new String("oke"));
		} else {
			response = Utils.creatResponseJson("addUser2Account", new String("fail"));
		}
			
			
		messagingTemplate.convertAndSendToUser(Utils.getUserPrincipal(accessor),
												"/queue/reply",
												response);
	}
}
