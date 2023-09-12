package com.dev.controllers;

import com.dev.responses.BasicResponse;
import com.dev.responses.ImageResponse;
import com.dev.utils.Errors;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BasicResponse uploadImage(@RequestParam("image") MultipartFile imageFile, @RequestParam("username") String username) {
        BasicResponse basicResponse;
        try {
            String originalFilename = imageFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = username + fileExtension;
            Path filePath = Paths.get(uploadDir, uniqueFilename);
            Files.write(filePath, imageFile.getBytes());
            System.out.println("Image uploaded for user " + username + " as: " + uniqueFilename);
            basicResponse=new ImageResponse(generateImageUrl(username));
            basicResponse.setSuccess(true);
        } catch (Exception e) {
            System.out.println("Failed to upload image");
            basicResponse=new BasicResponse(false,Errors.IMAGE_UPLOAD_FAILED);
        }
        return basicResponse;
    }

    private String generateImageUrl(String username) {
        // Construct the image URL based on the server's base URL and the username
        String baseUrl = "http://10.0.0.1:8989";
        String imageRelativePath = "images/" + username + ".jpg";
        return baseUrl + "/" + imageRelativePath;
    }


}