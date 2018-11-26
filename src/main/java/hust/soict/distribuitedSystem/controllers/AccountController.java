package hust.soict.distribuitedSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;



}