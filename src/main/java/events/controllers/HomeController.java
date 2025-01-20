package events.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import events.dataClasses.Event;
import events.dataClasses.User;
import events.repositories.EntryRepository;
import events.repositories.EventRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class HomeController {

	private final EventRepository eventRepo;
	private final EntryRepository entryRepo;

	@GetMapping("/")
	public String showHomePage(Model model, @ModelAttribute User user) {
		List<Event> userEvents = (List<Event>) entryRepo.findEventsByParttakerId(user.getId());
		List<Event> aviableEvents = (List<Event>) eventRepo.findAll();
		List<Event> organizedEvents = (List<Event>) eventRepo.findByCreatorId(user.getId());
		aviableEvents.removeIf(e -> userEvents.contains(e) || organizedEvents.contains(e));
		model.addAttribute("userEvents", userEvents);
		model.addAttribute("organizedEvents", organizedEvents);
		model.addAttribute("aviableEvents", aviableEvents);
		return "home";
	}

	@ModelAttribute("user")
	public User createUser() {
		return new User();
	}
}