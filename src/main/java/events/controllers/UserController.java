package events.controllers;

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
	public String signUpForm() {
		return "sign_up_form";
	}
	
	
	@PostMapping("/sign_up")
	public String registrateUser(@ModelAttribute User user) {
		userRepo.save(user);
		return "redirect:/userprofile";
	}
	
	
	@GetMapping("/sign_in")
	public String loginForm(Model model) {
		model.addAttribute("tempUser", new User());
		return "sign_in_form";
	}
	
	
	@PostMapping("/sign_in")
	public String loginUser(Model model, @ModelAttribute("tempUser") User tempUser) {
		User user = userRepo.findByName(tempUser.getName());
		if (user == null || !tempUser.getPassword().equals(user.getPassword())) {
			System.out.println(model.getAttribute("tempUser"));
			model.asMap().remove("tempUser");
			System.out.println(model.getAttribute("tempUser"));
			model.asMap().remove("tempUser");
			return "redirect:/";
		}
		model.addAttribute("User", user);
		return "redirect:/userprofile";
	}
	
	
	@GetMapping
	public String profile() {
		return "userprofile";
	}
	
	
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("user", new User());
		return "redirect:/";
		
	}
}
