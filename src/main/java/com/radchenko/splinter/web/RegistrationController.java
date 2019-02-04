package com.radchenko.splinter.web;

import com.radchenko.splinter.service.UserService;
import com.radchenko.splinter.web.request.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registrationView(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "registration";
    }


    @PostMapping
    public String registration(@Valid UserRegModel regModel, Model model) {
        if (userService.findByEmail(regModel.getEmail()).isPresent()){
            model.addAttribute("error", "user with email already exist");
            return "redirect:/registration";
        }

        userService.register(regModel);
//        model.addAttribute("message", "User created. Please log in");
        return "redirect:/login?message=test";//TODO: not displaying message
    }

}
