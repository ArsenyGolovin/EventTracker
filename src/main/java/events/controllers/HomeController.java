package events.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import events.dataClasses.User;
import events.repositories.EventRepository;

@Controller
@SessionAttributes("user")
public class HomeController {

	private final EventRepository eventRepo;

	public HomeController(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}

	@GetMapping("/")
	public String showHomePage(Model model, @ModelAttribute User user) {
		model.addAttribute("events", eventRepo.findAll());
		return "home";
	}

	@ModelAttribute("user")
	public User createUser() {
		return new User();
	}
}