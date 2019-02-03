package com.radchenko.splinter.web.rest;

import com.radchenko.splinter.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/{id}")
    public byte[] get(@PathVariable(name = "id") Long id) throws IOException {
        return imageService.getImage(id);
    }

    @PostMapping(path = "/{id}/delete")
    public void delete(@PathVariable(name = "id") Long id) throws IOException {
        imageService.deleteImage(id);
    }
}
