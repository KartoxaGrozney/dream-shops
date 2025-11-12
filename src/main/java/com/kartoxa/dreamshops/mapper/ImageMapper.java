package com.kartoxa.dreamshops.mapper;

import com.kartoxa.dreamshops.dto.ImageDto;
import com.kartoxa.dreamshops.model.Image;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {

    public ImageDto toDto(Image image) {
        if (image == null) {
            return null;
        }

        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setFileName(image.getFileName());
        dto.setFileType(image.getFileType());
        dto.setDownloadUrl(image.getDownloadUrl());

        return dto;
    }

    public Image toEntity(ImageDto dto) {
        if (dto == null) {
            return null;
        }

        Image image = new Image();
        image.setId(dto.getId());
        image.setFileName(dto.getFileName());
        image.setFileType(dto.getFileType());
        image.setDownloadUrl(dto.getDownloadUrl());

        return image;
    }

    public List<ImageDto> toDtoList(List<Image> images) {
        return images.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
