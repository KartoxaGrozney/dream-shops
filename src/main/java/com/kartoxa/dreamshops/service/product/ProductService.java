package com.kartoxa.dreamshops.service.product;

import com.kartoxa.dreamshops.dto.ProductDto;
import com.kartoxa.dreamshops.exceptions.ProductNotFoundException;
import com.kartoxa.dreamshops.mapper.ProductMapper;
import com.kartoxa.dreamshops.model.Product;
import com.kartoxa.dreamshops.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,() -> {throw new ProductNotFoundException("Product not found");});

        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, product))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, Product product) {
        existingProduct.setName(product.getName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setInventory(product.getInventory());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByCategoryName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }


    //DTO

    public ProductDto getProductDtoById(Long id){
        Product product = getProductById(id);
        return productMapper.toDto(product);
    }

    public List<ProductDto> getAllProductsDto(){
        List<Product> products = getAllProducts();
        return productMapper.toDtoList(products);
    }

    public ProductDto addProductDto(ProductDto productDto){
        Product product = productMapper.toEntity(productDto);

        Product savedProduct = productRepository.save(product);

        return productMapper.toDto(savedProduct);
    }

    public ProductDto updateProductDto(ProductDto productDto, Long productId) {
        Product existingProduct = getProductById(productId);

        existingProduct.setName(productDto.getName());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setInventory(productDto.getInventory());
        existingProduct.setDescription(productDto.getDescription());

        Product updatedProduct = productRepository.save(existingProduct);

        return productMapper.toDto(updatedProduct);
    }
}
