package com.kartoxa.dreamshops.service.category;

import com.kartoxa.dreamshops.exceptions.CategoryNotFoundException;
import com.kartoxa.dreamshops.exceptions.ProductNotFoundException;
import com.kartoxa.dreamshops.model.Category;
import com.kartoxa.dreamshops.model.Product;
import com.kartoxa.dreamshops.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found!"));
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return categoryRepository.findById(id)
                .map(existingCategory -> updateExistingCategory(existingCategory, category))
                .map(categoryRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    private Category updateExistingCategory(Category existingCategory, Category category) {
        existingCategory.setName(category.getName());
        existingCategory.setProducts(category.getProducts());
        return existingCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete,() -> {throw new ProductNotFoundException("Product not found");});

    }
}
