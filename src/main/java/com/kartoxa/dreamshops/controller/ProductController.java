package com.kartoxa.dreamshops.controller;

import com.kartoxa.dreamshops.model.Product;
import com.kartoxa.dreamshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-brand-and-name")
    public ResponseEntity<List<Product>> getProductsByBrandAndName(
            @RequestParam String brand,
            @RequestParam String name) {
        List<Product> products = productService.getProductsByBrandAndName(brand, name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-category-and-brand")
    public ResponseEntity<List<Product>> getProductsByCategoryAndBrand(
            @RequestParam String category,
            @RequestParam String brand) {
        List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam String name) {
        List<Product> products = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-brand")
    public ResponseEntity<List<Product>> getProductsByBrand(@RequestParam String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/count-by-brand-and-name")
    public ResponseEntity<Long> countProductsByBrandAndName(
            @RequestParam String brand,
            @RequestParam String name) {
        Long count = productService.countProductsByBrandAndName(brand, name);
        return ResponseEntity.ok(count);
    }
}
