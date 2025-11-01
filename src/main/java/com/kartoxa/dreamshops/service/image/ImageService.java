package com.kartoxa.dreamshops.service.image;

import com.kartoxa.dreamshops.exceptions.ImageNotFoundException;
import com.kartoxa.dreamshops.model.Image;
import com.kartoxa.dreamshops.model.Product;
import com.kartoxa.dreamshops.repository.ImageRepository;
import com.kartoxa.dreamshops.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService{

    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        Product product = productService.getProductById(productId);

        Image image = new Image();

        try{
            image.setFileName(file.getName());
            image.setFileType(file.getContentType());
            image.setImage(file.getBytes());
        }
        catch(IOException e){
            throw new RuntimeException("Failed to read file:" + file.getOriginalFilename(),e);
        }

        image.setProduct(product);

        Image savedImage = imageRepository.save(image);

        savedImage.setDownloadUrl("/api/images/download/" + savedImage.getId());

        return imageRepository.save(savedImage);

    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found with id:" + id));
    }

    @Override
    public List<Image> getImagesByProductId(Long productId) {
        return imageRepository.findByProductId(productId);
    }

    @Override
    public void updateFile(MultipartFile file, Long imageId) {

        if(file.isEmpty()){
            throw new IllegalArgumentException("File is empty");
        }

        if(file.getContentType() == null){
            throw new IllegalArgumentException("File must be an image");
        }

        if (file.getSize() > 5 * 1024 * 1024) {  // 5 МБ
            throw new IllegalArgumentException("File size must not exceed 5MB");
        }

        Image existingImage = getImageById(imageId);

        try{
            existingImage.setFileName(file.getName());

            existingImage.setFileType(file.getContentType());

            existingImage.setImage(file.getBytes());
        }
        catch(IOException e){
            throw new RuntimeException("Failed to read file: " + file.getName());
        }

        imageRepository.save(existingImage);
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {throw new ImageNotFoundException("Image not found");});
    }
}
