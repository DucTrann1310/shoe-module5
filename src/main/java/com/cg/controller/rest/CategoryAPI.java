package com.cg.controller.rest;


import com.cg.model.Category;
import com.cg.service.categoryService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shoe-ecommerce/category")
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories(){

        List<Category> categoryList = categoryService.findAll();

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
