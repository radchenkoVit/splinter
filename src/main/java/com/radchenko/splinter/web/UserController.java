package com.radchenko.splinter.web;

import com.radchenko.splinter.service.UserService;
import com.radchenko.splinter.web.response.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String usersView(Model model) {
        model.addAttribute("users", userService.getAll());

        return "users";
    }

    @GetMapping(path = "/{id}")
    public String editView(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "userEdit";
    }

    @PostMapping(path = "/edit")
    public String test(@Valid UserDto editUser){
        userService.update(editUser);
        return "redirect:/user";
    }
}
