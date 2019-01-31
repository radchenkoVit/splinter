package com.radchenko.splinter.web;

import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.entity.User;
import com.radchenko.splinter.service.MessageService;
import com.radchenko.splinter.service.auth.UserPrincipal;
import com.radchenko.splinter.web.response.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String messageView(Model model) {
        model.addAttribute("messages", messageService.getAll());
        return "index";
    }

    @PostMapping
    public String addMessage(
                            @AuthenticationPrincipal UserPrincipal user,//TODO: офигеть просто
                            @RequestParam(name = "text") String text,
                            @RequestParam(name = "tag") String tag) {
        Message message = new Message(null, text, tag, user.getUser());
        messageService.save(message);
        return "redirect:/";
    }

    @PostMapping
    @RequestMapping(path = "/filter")
    public String filterMessage(@RequestParam(name = "msg_tag_filter") String tagFilter, Model model) {
        if (tagFilter != null && !tagFilter.isEmpty()) {
            List<MessageDto> list = messageService.filterByTag(tagFilter);
            model.addAttribute("messages", list);
        } else {
            model.addAttribute("messages", messageService.getAll());
        }

        return "index";
    }

}
