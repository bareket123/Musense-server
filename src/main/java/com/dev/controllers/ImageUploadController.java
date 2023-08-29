package com.dev.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageUploadController {

    @Value("${upload.dir}")
    private String uploadDir;

        @PostMapping("/upload")
        public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) {
            try {
                Path filePath = Paths.get(uploadDir, imageFile.getOriginalFilename());
                System.out.println("uploadDir: " + uploadDir); // Print the upload directory

                Files.write(filePath, imageFile.getBytes());
                System.out.println("upload");
                return ResponseEntity.ok("Image uploaded successfully");
            } catch (Exception e) {
                System.out.println("failed");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
            }
        }
    }
