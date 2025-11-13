package com.kartoxa.dreamshops.service.image;

import com.kartoxa.dreamshops.dto.ImageDto;
import com.kartoxa.dreamshops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image saveImage(MultipartFile file, Long productId);

    Image getImageById(Long id);

    List<Image> getImagesByProductId(Long productId);

    Image updateImage(MultipartFile file, Long imageId);

    void deleteImage(Long id);

    ImageDto getImageDtoById(Long id);

    List<ImageDto> getImagesDtoByProductId(Long productId);
}
