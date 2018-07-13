package com.example.demo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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



//    @Autowired
//    UserDetailsService userDetailsService;

//    @RequestMapping("/addfriend")
//    public String addroom(Model model) {
//        model.addAttribute("aFriend", new Friend());
//
//        return "addfriend";
//    }

//    @GetMapping("/displayfriend")
//    public String displayFriend(Model model) {
//        model.addAttribute("friends", getCurrentUser().getFriends());
//        return "displayfriend";
//    }

    @RequestMapping("/displayit")
    public String showHomepage(Model model){
        model.addAttribute("items", itemRepository.findAll());
        return "displayitem";
    }

    @RequestMapping("/displayusers")
    public String showAllUsers(Model model){
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
    public ModelAndView addNewUser(@ModelAttribute("newUser") User newUser, BindingResult result, Model model, ModelAndView modelAndView) {
        User userExists = userRepository.findByEmail(newUser.getEmail());
        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
            result.reject("email");
        }
        else
        {
            userExists = userRepository.findByUserName(newUser.getUserName());
            if (userExists != null) {
                modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the user Name provided.");
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

//    @RequestMapping("/savefriend")
//    public String savePet(@Valid @ModelAttribute("aFriend") Friend friend, BindingResult result, Model model,
//                          @RequestParam("file") MultipartFile file) {
//        System.out.println(result.toString());
//
//        if (file.isEmpty()) {
//            return "redirect:/addfriend";
//        }
//        User user = getCurrentUser();
//        try {
//            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
//            friend.setUrlImage(uploadResult.get("url").toString());
//            user.getFriends().add(friend);
//            userRepository.save(user);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "redirect:/addfriend";
//        }
//
//        // model.addAttribute("friends",friendRepositroy.findAllByFilledByOrderByRankOfFriend(friend.getFilledBy()));
//        model.addAttribute("friends", user.getFriends());
//
//        return "displayfriend";
//    }
@RequestMapping("/additem")
public String addRoom(Model model){
    model.addAttribute("anItem", new Item());
    return "additem";
}
    @RequestMapping("/updateliste/{id}")
    public String updateRoom(@PathVariable("id") long id, Model model){
        Item anItem = itemRepository.findById(id).get();
        anItem.setSoldout(true);
        itemRepository.save(anItem);
        model.addAttribute("items", itemRepository.findAll());
        return "displayitem";
    }

    @RequestMapping("/saveitem")
    public String saveRoom(@ModelAttribute("anItem") Item item, Model model){
        itemRepository.save(item);
        model.addAttribute("items", itemRepository.findAll());
        return "displayitem";
    }
    @RequestMapping("/search")
    public String searchPromises(Model model, HttpServletRequest request)
    {
        String searchedFor = request.getParameter("searchfor");
        model.addAttribute("searchResults",true);
        bringItService.searchItem(model,searchedFor);
        return "displayitem";
    }



    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userRepository.findUserClassByUserName(userName);
        return user;
    }

}
