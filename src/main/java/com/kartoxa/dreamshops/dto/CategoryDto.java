package com.kartoxa.dreamshops.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    private List<Long> productIds;
}
