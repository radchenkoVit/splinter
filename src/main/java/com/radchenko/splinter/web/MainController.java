package com.radchenko.splinter.web;

import com.radchenko.splinter.MessageService;
import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.lang.String.format;

@Controller
@RequestMapping(path = "/")
public class MainController {

    private final UserRepository userRepository;
    private final MessageService messageService;

    @Autowired
    public MainController(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @GetMapping(path = "/hello")
    @ResponseBody
    public String mainView(@RequestParam(name = "name", defaultValue = "World") String name) {
        return format("Hello %s!", name);
    }

    @GetMapping
    public String messageView(Model model) {
        model.addAttribute("messages", messageService.getAll());
        return "index";
    }

    @PostMapping
    public String addMessage(@RequestParam(name = "text") String text,
                           @RequestParam(name = "tag") String tag) {
        Message message = new Message(null, text, tag);
        messageService.save(message);
        return "redirect:/";
    }

    @PostMapping
    @RequestMapping(path = "/filter")
    public String filterMessage(@RequestParam(name = "msg_tag_filter") String tagFilter, Model model) {
        if (tagFilter != null && !tagFilter.isEmpty()) {
            List<Message> list = messageService.filterByTag(tagFilter);
            model.addAttribute("messages", list);
        }

        return "index";
    }

}
