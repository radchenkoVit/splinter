package com.radchenko.splinter.web;

import com.radchenko.splinter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping(path = "/{id}")//TODO: we can put User user --> spring will do magic
    public String editView(@PathVariable Long id, Model model) {
        return "userEdit";
    }
}
