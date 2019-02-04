package com.radchenko.splinter.web;

import com.radchenko.splinter.entity.Image;
import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.service.ImageService;
import com.radchenko.splinter.service.MessageService;
import com.radchenko.splinter.service.auth.UserPrincipal;
import com.radchenko.splinter.web.response.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping(path = "/")
public class MainController {
    private final MessageService messageService;
    private final ImageService imageService;

    @Autowired
    public MainController(MessageService messageService, ImageService imageService) {
        this.messageService = messageService;
        this.imageService = imageService;
    }

    @GetMapping
    public String messageView(@RequestParam(name = "msg_tag_filter", required = false) String tagFilter,
                              Model model,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 20) Pageable page) {
        Collection<MessageDto> messages = tagFilter != null && !tagFilter.isEmpty() ? messageService.filterByTag(tagFilter, page) : messageService.getAll(page);
        String tagAttr = tagFilter != null && !tagFilter.isEmpty() ? tagFilter.trim() : "";

        model.addAttribute("messages", messages);
        model.addAttribute("tagFilter", tagAttr);

        return "index";
    }

    @PostMapping
    public String addMessage(
            @AuthenticationPrincipal UserPrincipal user,//TODO: офигеть просто
            @RequestParam(name = "text") String text,
            @RequestParam(name = "tag") String tag,//TODO: fix IO exception
            @RequestParam(name = "imageFile", required = false) MultipartFile file) throws IOException {
        Message message = new Message(null, text, tag, user.getUser(), null);

        //TODO: refactor
        if (file != null && !file.isEmpty()) {
            Image image = new Image();
            image.setPath(imageService.saveImage(file));
            message.setImage(image);
            image.setMessage(message);
        }

        messageService.save(message);
        return "redirect:/";
    }
}
