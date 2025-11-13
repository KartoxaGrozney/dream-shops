package com.kartoxa.dreamshops.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ImageDto {
    private Long id;

    private String fileName;

    private String fileType;

    private String downloadUrl;

}
