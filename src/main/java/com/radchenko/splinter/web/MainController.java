package com.radchenko.splinter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.lang.String.format;

@Controller
public class MainController {

    @GetMapping(path = "/hello")
    @ResponseBody
    public String mainView(@RequestParam(name = "name", defaultValue = "World") String name) {
        return format("Hello %s!", name);
    }
}
