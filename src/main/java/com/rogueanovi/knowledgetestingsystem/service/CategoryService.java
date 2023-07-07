package com.rogueanovi.knowledgetestingsystem.service;

import com.rogueanovi.knowledgetestingsystem.model.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getCategoriesList();
    Category getCategoryById(Long categoryId) throws Exception;
    Category AddCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long categoryId);
}
