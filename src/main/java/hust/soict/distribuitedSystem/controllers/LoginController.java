package hust.soict.distribuitedSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import hust.soict.distribuitedSystem.entities.User;
import hust.soict.distribuitedSystem.repositories.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/app/login", method=RequestMethod.POST)
	public String login(@RequestParam(name="username") String username,
						@RequestParam(name="password") String password) {
		List<User> users = userRepository.fetchUserBy(username, password);
		if (users.isEmpty()) {
			return "re_login";
		} else {
			User user = users.get(0);
			if (user.getRole() == 1) {
				return "customer";
			} else {
				return "banker";
			}
		}
	}
}
