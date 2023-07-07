package com.rogueanovi.knowledgetestingsystem.controller;

import com.rogueanovi.knowledgetestingsystem.dto.category.CategoryAddRequestDto;
import com.rogueanovi.knowledgetestingsystem.dto.category.CategoryUpdateRequestDto;
import com.rogueanovi.knowledgetestingsystem.model.Category;
import com.rogueanovi.knowledgetestingsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<Set<Category>> getCategoriesList(){
        Set<Category> categoriesList = categoryService.getCategoriesList();
        return new ResponseEntity<>(categoriesList, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId){
        try {
            Category category = categoryService.getCategoryById(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryAddRequestDto categoryAddRequestDto){
        try {
            Category categoryEntity = categoryAddRequestDto.convertToEntity();
            Category categorySaved = categoryService.AddCategory(categoryEntity);
            return new ResponseEntity<>(categorySaved, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto){
        Category categoryEntity = categoryUpdateRequestDto.convertToEntity();
        try {
            Category categoryFound =  categoryService.getCategoryById(categoryId);
            categoryFound.setTitle(categoryEntity.getTitle());
            categoryFound.setDescription(categoryEntity.getDescription());

            Category categoryUpdated = categoryService.updateCategory(categoryFound);
            return  new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("categoryId") Long categoryId){
        try {
            Category categoryFound = categoryService.getCategoryById(categoryId);
            categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>( categoryFound, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
