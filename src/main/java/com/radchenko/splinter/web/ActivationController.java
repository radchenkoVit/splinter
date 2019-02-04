package com.radchenko.splinter.web;

import com.radchenko.splinter.service.UserService;
import com.radchenko.splinter.web.response.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.String.format;

@Controller
@RequestMapping(path = "/activate")
public class ActivationController {

    private final UserService userService;

    @Autowired
    public ActivationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{code}")
    public String activateAccount(@PathVariable String code, Model model) {
        UserDto user = userService.activateUser(code);
        model.addAttribute("message", format("Account[%s] is activated", user.getEmail()));

        return "login";
    }
}
