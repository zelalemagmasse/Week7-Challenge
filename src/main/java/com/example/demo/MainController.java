package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	BringItService bringItService;

	@RequestMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("items", itemRepository.getTop10());
		return "/index";
	}

	@RequestMapping("/displayit")
	public String showHomepage(Model model) {
		model.addAttribute("items", itemRepository.findAll());
		return "displayitem";
	}

	@RequestMapping("/displayusers")
	public String showAllUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "displayusers";
	}

	@GetMapping("/register")
	public ModelAndView registerUser(ModelAndView modelAndView, User user) {
		modelAndView.addObject("newUser", user);
		modelAndView.setViewName("register");
		return modelAndView;

	}

	@PostMapping("/register")
	public ModelAndView addNewUser(@ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			ModelAndView modelAndView) {
		User userExists = userRepository.findByEmail(newUser.getEmail());
		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage",
					"Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			result.reject("email");
		} else {
			userExists = userRepository.findByUserName(newUser.getUserName());
			if (userExists != null) {
				modelAndView.addObject("alreadyRegisteredMessage",
						"Oops!  There is already a user registered with the user Name provided.");
				modelAndView.setViewName("register");
				result.reject("userName");
			}
		}

		if (result.hasErrors()) {
			modelAndView.setViewName("register");
		} else {
			// Create a new ordinary user
			modelAndView.addObject(newUser.getUserName() + " created");
			Role r = roleRepository.findByRole("USER");
			newUser.addRole(r);
			userRepository.save(newUser);
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}

	@RequestMapping("/suspend/{userId}")
	public String suspend(@PathVariable String userId, Model model) {
		User user = userRepository.findById(Long.parseLong(userId)).get();
		user.setSuspended(true);
		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return "displayusers";
	}

	@RequestMapping("/unsuspend/{userId}")
	public String unsuspend(@PathVariable String userId, Model model) {
		User user = userRepository.findById(Long.parseLong(userId)).get();
		user.setSuspended(false);
		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return "displayusers";
	}

	@RequestMapping("/additem")
	public String addRoom(Model model) {
		model.addAttribute("anItem", new Item());
		return "additem";
	}

	@RequestMapping("/updateliste/{id}")
	public String updateRoom(@PathVariable("id") long id, Model model) {
		Item anItem = itemRepository.findById(id).get();
		anItem.setSoldout(true);
		itemRepository.save(anItem);
		model.addAttribute("items", itemRepository.findAll());
		return "displayitem";
	}

	@RequestMapping("/saveitem")
	public String saveRoom(@ModelAttribute("anItem") Item item, Model model) {
		itemRepository.save(item);
		model.addAttribute("items", itemRepository.findAll());
		return "displayitem";
	}

	@RequestMapping("/search")
	public String searchPromises(Model model, HttpServletRequest request) {
		String searchedFor = request.getParameter("searchfor");
		model.addAttribute("searchResults", true);
		bringItService.searchItem(model, searchedFor);
		return "displayitem";
	}


}
