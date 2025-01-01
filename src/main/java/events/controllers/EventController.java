package events.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import events.dataClasses.Entry;
import events.dataClasses.Event;
import events.dataClasses.User;
import events.repositories.EntryRepository;
import events.repositories.EventRepository;
import events.repositories.UserRepository;

@Controller
@SessionAttributes("user")
@RequestMapping("/events")
public class EventController {

	private final EventRepository eventRepo;
	private final EntryRepository entryRepo;

	public EventController(UserRepository userRepo, EventRepository eventRepo, EntryRepository entryRepo) {
		this.eventRepo = eventRepo;
		this.entryRepo = entryRepo;
	}

	@GetMapping("/{eventId}")
	public String showEventById(Model model, @PathVariable int eventId) {
		Optional<Event> event = eventRepo.findById(eventId);
		if (event.isEmpty()) {
			model.addAttribute("message", "Мероприятие не найдено.");
			return "error";
		}
		model.addAttribute("event", event.get());
		model.addAttribute("parttakers", entryRepo.findParttakersByEventId(event.get().getId()));
		return "event";
	}

	@GetMapping("/create")
	public String showEventCreationForm(Model model) {
		model.addAttribute("event", new Event());
		return "event_creation_form";
	}

	@PostMapping("/create")
	public String createEvent(Model model, @ModelAttribute Event event) {
		User user = (User) model.getAttribute("user"); // Don't add via @ModelAttribute -
														// fields "name" of Event and User will be mixed up.
		event.setCreatorId(user.getId());
		event = eventRepo.save(event);
		return "redirect:/events/" + event.getId();
	}

	@PostMapping(path = "/add-or-delete-parttaker", headers = "hx-request=true")
	private String addOrDeleteParttaker(Model model, @ModelAttribute User user, @RequestParam int eventId) {
		List<User> parttakers = entryRepo.findParttakersByEventId(eventId);
		if (parttakers.contains(user))
			entryRepo.deleteByParttakerIdAndEventId(user.getId(), eventId);
		else
			entryRepo.save(new Entry(user.getId(), eventId));
		model.addAttribute("parttakers", entryRepo.findParttakersByEventId(eventId));
		model.addAttribute("event", eventRepo.findById(eventId).get());
		return "_fragments :: btn-and-parttakers";
	}
}