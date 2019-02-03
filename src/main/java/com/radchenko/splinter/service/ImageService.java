package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.Image;
import com.radchenko.splinter.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.String.format;

@Service
public class ImageService {
    @Value("${upload.file}")//TODO: profile depends prop
    private String UPLOAD_ROOT;

    private final ResourceLoader resourceLoader;
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ResourceLoader resourceLoader, ImageRepository imageRepository) {
        this.resourceLoader = resourceLoader;
        this.imageRepository = imageRepository;
    }

    public byte[] getImage(Long id) throws IOException {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Image with id: %s not found", id)));

        return Files.readAllBytes(resourceLoader.getResource("file:" + image.getPath()).getFile().toPath());//TODO: cross OS ISSUE
    }

    public String saveImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
//            Path filePath = Paths.get(UPLOAD_ROOT, UUID.randomUUID().toString(), file.getOriginalFilename());//TODO: not create uuid folder
            Path filePath = Paths.get(UPLOAD_ROOT, file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath);
            return filePath.toString();
        }

        throw new RuntimeException("Empty file");
    }

    @Transactional
    public void deleteImage(Long id) throws IOException {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Image with id: %s not found", id)));

        Files.deleteIfExists(new File(image.getPath()).toPath());
        imageRepository.delete(image);
    }


    //TODO for future
    @Bean
//    @Profile("dev")
    CommandLineRunner setUp() {
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));
            Files.createDirectories(Paths.get(UPLOAD_ROOT));
        };
    }
}
