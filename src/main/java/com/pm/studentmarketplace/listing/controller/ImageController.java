package com.pm.studentmarketplace.listing.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("uploads/")
public class ImageController {
    private final Path uploadRoot = Paths.get("uploads/listings");

    @GetMapping("{filename}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename){
        Path file = uploadRoot.resolve(filename);
        Resource resource = new FileSystemResource(file);

        if(!resource.exists()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
