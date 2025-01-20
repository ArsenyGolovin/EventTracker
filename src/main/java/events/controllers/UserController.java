package events.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import events.dataClasses.Event;
import events.dataClasses.User;
import events.repositories.EntryRepository;
import events.repositories.EventRepository;
import events.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes("user")
@RequiredArgsConstructor
@RequestMapping("/userprofile")
public class UserController {

	private final UserRepository userRepo;
	private final EventRepository eventRepo;
	private final EntryRepository entryRepo;

	@GetMapping("/sign_up")
	public String showSignUpForm(Model model, @RequestParam(required = false) Integer eventId) {
		model.addAttribute("user", new User());
		return "sign_up_form";
	}

	@PostMapping("/sign_up")
	public String registrateUser(@ModelAttribute User user, @RequestParam(required = false) Integer eventId) {
		// TODO validation
		userRepo.save(user);
		if (eventId == null)
			return "redirect:/userprofile";
		return "redirect:/events/" + eventId;
	}

	@GetMapping("/sign_in")
	public String showLoginForm(Model model, @RequestParam(required = false) Integer eventId) {
		model.addAttribute("tempUser", new User());
		return "sign_in_form";
	}

	@PostMapping("/sign_in")
	public String loginUser(Model model, @ModelAttribute User tempUser,
			@RequestParam(required = false) Integer eventId) {
		// TODO validation
		Optional<User> u = userRepo.findByName(tempUser.getName());
		if (u.isPresent()) {
			User user = u.get();
			if (!tempUser.getPassword().equals(user.getPassword()))
				return "redirect:/sign_in";
			model.addAttribute("user", user);
			if (eventId == null)
				return "redirect:/userprofile";
			return "redirect:/events/" + eventId;
		}
		return "redirect:/sign_in";
	}

	@GetMapping
	public String showProfile(Model model, @ModelAttribute User user) {
		List<Event> userEvents = (List<Event>) entryRepo.findEventsByParttakerId(user.getId());
		Collections.sort(userEvents);
		List<Event> organizedEvents = (List<Event>) eventRepo.findByCreatorId(user.getId());
		Collections.sort(organizedEvents);
		List<Event> aviableEvents = (List<Event>) eventRepo.findAll();
		aviableEvents.removeIf(e -> userEvents.contains(e) || organizedEvents.contains(e));
		Collections.sort(aviableEvents);
		model.addAttribute("userEvents", userEvents);
		model.addAttribute("organizedEvents", organizedEvents);
		model.addAttribute("aviableEvents", aviableEvents);
		return "userprofile";
	}

	@GetMapping("/logout")
	public String logoutUser(Model model) {
		model.addAttribute("user", new User());
		return "redirect:/";
	}
}
