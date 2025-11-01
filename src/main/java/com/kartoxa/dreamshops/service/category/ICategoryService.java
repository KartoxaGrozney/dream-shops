package com.kartoxa.dreamshops.service.category;

import com.kartoxa.dreamshops.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category getCategoryById(Long id);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}