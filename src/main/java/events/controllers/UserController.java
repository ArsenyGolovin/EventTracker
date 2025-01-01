package events.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import events.dataClasses.User;
import events.repositories.UserRepository;

@Controller
@SessionAttributes("user")
@RequestMapping("/userprofile")
public class UserController {

	private final UserRepository userRepo;

	public UserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/sign_up")
	public String showSignUpForm(Model model){
		model.addAttribute("user", new User());
		return "sign_up_form";
	}

	@PostMapping("/sign_up")
	public String registrateUser(@ModelAttribute User user) {
		// TODO validation
		userRepo.save(user);
		return "redirect:/userprofile";
	}

	@GetMapping("/sign_in")
	public String showLoginForm(Model model) {
		model.addAttribute("tempUser", new User());
		return "sign_in_form";
	}

	@PostMapping("/sign_in")
	public String loginUser(Model model, @ModelAttribute("tempUser") User tempUser) {
		// TODO validation
		Optional<User> user = userRepo.findByName(tempUser.getName());
		if (user.isEmpty() || !tempUser.getPassword().equals(user.get().getPassword()))
			return "redirect:/";
		model.addAttribute("user", user.get());
		return "redirect:/userprofile";
	}

	@GetMapping
	public String profile() {
		return "userprofile";
	}

	@GetMapping("/logout")
	public String logoutUser(Model model) {
		model.addAttribute("user", new User());
		return "redirect:/";
	}
}
