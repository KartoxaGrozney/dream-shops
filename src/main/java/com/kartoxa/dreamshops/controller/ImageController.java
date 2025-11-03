package com.kartoxa.dreamshops.controller;

import com.kartoxa.dreamshops.model.Image;
import com.kartoxa.dreamshops.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final IImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id){
        return ResponseEntity.ok(imageService.getImageById(id));
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam MultipartFile file, @RequestParam Long productId){
        return ResponseEntity.ok(imageService.saveImage(file, productId));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id){
        Image image = imageService.getImageById(id);

        return ResponseEntity.ok().header("Content-Type", image.getFileType())
                .header("Content-Disposition", "attachment; filename=\"" + image.getFileName() + "\"")
                .body(image.getImage());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id){
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Image> updateImage(
            @RequestParam MultipartFile file,
            @PathVariable Long id) {
        return ResponseEntity.ok(imageService.updateImage(file, id));
    }

}
