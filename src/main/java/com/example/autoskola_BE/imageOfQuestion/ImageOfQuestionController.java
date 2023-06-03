package com.example.autoskola_BE.imageOfQuestion;

import com.example.autoskola_BE.images.Image;
import com.example.autoskola_BE.images.ImageUploadResponse;
import com.example.autoskola_BE.images.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageOfQuestionController {

    @Autowired
    ImageOfQuestionRepository imageOfQuestionRepository;

    @Primary
    @PostMapping("/saveTestImage")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        imageOfQuestionRepository.save(ImageOfQuestion.builder()
                .type(file.getContentType())
                .image(ImageUtility.compressAndReduceImageQuality(file.getBytes(), file.getContentType())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));

    }

}
