package com.qualitapps.mangoapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.entities.Category;
import com.qualitapps.mangoapp.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<List<Category>> getAllCategories() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<Category> allCategories = new ArrayList<>();
        try {
            allCategories = categoryRepository.findAll();
            httpStatus= HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<List<Category>>(allCategories, httpStatus);
    }

    public ResponseEntity<Category> saveCategory(Category category) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            category.setCreatedDate(new Date());
            category.setIsDeleted(false);
            category = categoryRepository.save(category);
            httpStatus= HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Category>(category, httpStatus);
    }

    public ResponseEntity<Category> updateCategory(Category category) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
        try {
           Optional<Category> updateModule = categoryRepository.findById(category.getId());
           if(updateModule.isPresent()){
                updateModule.get().setUpdatedBy("Admin");
                updateModule.get().setUpdatedDate(new Date());
                updateModule.get().setCategoryType(category.getCategoryType());
                updateModule.get().setCategoryTypeKey(category.getCategoryTypeKey());
                category = categoryRepository.saveAndFlush(updateModule.get());
                httpStatus= HttpStatus.OK;
           }

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Category>(category,httpStatus);
    }

    public ResponseEntity<Boolean> deleteCategory(Integer categoryId) {
        Boolean isDelete = false;
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if(category.isPresent()){
                category.get().setIsDeleted(true);
                categoryRepository.saveAndFlush(category.get());
                isDelete = true;
                httpStatus = HttpStatus.OK;
            } 
        } catch (Exception e) {
           e.printStackTrace();
        }
        return new ResponseEntity<Boolean>(isDelete,httpStatus);
    }

}
