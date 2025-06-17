package com.finalproject.Backend.controller;

import com.finalproject.Backend.model.Category;
import com.finalproject.Backend.service.CategoryService; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    private final CategoryService categoryService; 

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}