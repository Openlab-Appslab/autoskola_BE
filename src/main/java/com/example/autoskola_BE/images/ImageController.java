package com.example.autoskola_BE.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin()
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Primary
    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        //Ukladá pomocou compresie obrazok v bytovoj podobe a jeho typ
        imageRepository.save(Image.builder()
                .type(file.getContentType())
                .image(ImageUtility.compressAndReduceImageQuality(file.getBytes(), file.getContentType())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));

    }

    @GetMapping(path = {"/get/image/info/{id}"})
    public Image getImageDetails(@PathVariable("id") Long id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return Image.builder()
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }


    @GetMapping(path = {"/get/image/{id}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
}
