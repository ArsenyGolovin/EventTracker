package events.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import events.dataClasses.User;


@Controller
@SessionAttributes("user")
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	@ModelAttribute("user")
	public User user() {
		return new User();
	}
}