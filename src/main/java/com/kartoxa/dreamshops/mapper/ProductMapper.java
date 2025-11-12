package com.kartoxa.dreamshops.mapper;

import com.kartoxa.dreamshops.dto.ProductDto;
import com.kartoxa.dreamshops.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product){

        if(product == null){
            return null;
        }

        ProductDto dto = new ProductDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setPrice(product.getPrice());
        dto.setInventory(product.getInventory());
        dto.setDescription(product.getDescription());

        if (product.getCategory() != null) {
            dto.setCategoryName(product.getCategory().getName());
        }

        if (product.getImages() != null && !product.getImages().isEmpty()) {
            List<String> imageUrls = product.getImages().stream()
                    .map(image -> image.getDownloadUrl())
                    .collect(Collectors.toList());
            dto.setImageUrls(imageUrls);
        }

        return dto;
    }

    public Product toEntity(ProductDto dto){
        if(dto == null){
            return null;
        }

        Product product = new Product();

        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setInventory(dto.getInventory());
        product.setDescription(dto.getDescription());

        return product;
    }

    public List<ProductDto> toDtoList(List<Product> products) {
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
