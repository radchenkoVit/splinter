package com.radchenko.splinter.web;

import com.radchenko.splinter.entity.Message;
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

import java.util.Collection;

@Controller
@RequestMapping(path = "/")
public class MainController {
    private final MessageService messageService;

    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String messageView(@RequestParam(name = "msg_tag_filter", required = false) String tagFilter, Model model) {
        Collection<MessageDto> messages = tagFilter != null && !tagFilter.isEmpty() ? messageService.filterByTag(tagFilter) : messageService.getAll();
        String tagAttr = tagFilter != null && !tagFilter.isEmpty() ? tagFilter : "";

        model.addAttribute("messages", messages);
        model.addAttribute("tagFilter", tagAttr);

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
}
