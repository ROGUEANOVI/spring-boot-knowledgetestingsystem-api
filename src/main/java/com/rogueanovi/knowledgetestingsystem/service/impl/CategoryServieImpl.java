package com.rogueanovi.knowledgetestingsystem.service.impl;

import com.rogueanovi.knowledgetestingsystem.model.Category;
import com.rogueanovi.knowledgetestingsystem.repository.CategoryRepository;
import com.rogueanovi.knowledgetestingsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoryServieImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Set<Category> getCategoriesList() {
        return new LinkedHashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    @Override
    public Category AddCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
