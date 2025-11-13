package com.kartoxa.dreamshops.controller;

import com.kartoxa.dreamshops.dto.ImageDto;
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

    @PostMapping("/upload")
    public ResponseEntity<ImageDto> uploadImage(
            @RequestParam MultipartFile file,
            @RequestParam Long productId) {
        Image savedImage = imageService.saveImage(file, productId);
        ImageDto imageDto = imageService.getImageDtoById(savedImage.getId());
        return ResponseEntity.ok(imageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> getImageById(@PathVariable Long id) {
        ImageDto imageDto = imageService.getImageDtoById(id);
        return ResponseEntity.ok(imageDto);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        Image image = imageService.getImageById(id);  // ← Используем Entity!

        return ResponseEntity.ok()
                .header("Content-Type", image.getFileType())
                .header("Content-Disposition", "attachment; filename=\"" + image.getFileName() + "\"")
                .body(image.getImage());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ImageDto> updateImage(
            @RequestParam MultipartFile file,
            @PathVariable Long id) {
        Image updatedImage = imageService.updateImage(file, id);
        ImageDto imageDto = imageService.getImageDtoById(updatedImage.getId());
        return ResponseEntity.ok(imageDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

}
