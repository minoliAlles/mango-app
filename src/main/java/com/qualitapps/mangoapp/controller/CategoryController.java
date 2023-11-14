package com.qualitapps.mangoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qualitapps.mangoapp.dto.ResponseDTO;
import com.qualitapps.mangoapp.entities.Category;
import com.qualitapps.mangoapp.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public ResponseEntity<ResponseDTO> getAllCategories() {
        
        ResponseEntity<List<Category>> allCategories = categoryService.getAllCategories();
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getAllCategories")
                .status(allCategories.getStatusCode().value())
                .version("v1")
                .response(allCategories.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, allCategories.getStatusCode());
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<ResponseDTO> saveCategory(@RequestBody Category category) {
        
        ResponseEntity<Category> newCategory = categoryService.saveCategory(category);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/saveCategory")
                .status(newCategory.getStatusCode().value())
                .version("v1")
                .response(newCategory.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, newCategory.getStatusCode());
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<ResponseDTO> updateCategory(@RequestBody Category category) {
        
        ResponseEntity<Category> updateCategory = categoryService.updateCategory(category);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/updateCategory")
                .status(updateCategory.getStatusCode().value())
                .version("v1")
                .response(updateCategory.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, updateCategory.getStatusCode());
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<ResponseDTO> deleteCategory(@RequestParam Integer categoryId) {
        
        ResponseEntity<Boolean> isExist = categoryService.deleteCategory(categoryId);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/deleteCategory")
                .status(isExist.getStatusCode().value())
                .version("v1")
                .response(isExist.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, isExist.getStatusCode());
    }
}
