package com.pm.studentmarketplace.listing.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {
    private final Path uploadRoot = Paths.get("uploads/listings");

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename){
        try{
            Path file = uploadRoot.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(!resource.exists()){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(resource);
        } catch (MalformedURLException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
