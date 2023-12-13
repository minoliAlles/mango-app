package com.qualitapps.mangoapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.CategoryFilterDTO;
import com.qualitapps.mangoapp.dto.CategoryDTO;
import com.qualitapps.mangoapp.dto.ModuleDTO;
import com.qualitapps.mangoapp.entities.Category;
import com.qualitapps.mangoapp.exception.AppServiceException;
import com.qualitapps.mangoapp.mapper.CategoryFilterMapper;
import com.qualitapps.mangoapp.mapper.CategoryMapper;
import com.qualitapps.mangoapp.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    @Autowired
    private CategoryFilterMapper categoryFilterMapper;

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
            category.setCategoryTypeKey(category.getModule()+"."+category.getCategoryType());
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
           Optional<Category> updateCategory = categoryRepository.findById(category.getId());
           if(updateCategory.isPresent()){
                updateCategory.get().setModule(category.getModule());
                updateCategory.get().setUpdatedBy("Admin");
                updateCategory.get().setUpdatedDate(new Date());
                updateCategory.get().setCategoryType(category.getCategoryType());
                updateCategory.get().setCategoryTypeKey(category.getModule()+"."+category.getCategoryType());
                updateCategory.get().setIsDeleted(category.getIsDeleted());
                category = categoryRepository.saveAndFlush(updateCategory.get());
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

    public ResponseEntity<CategoryDTO> getCategoryById(Integer id) throws AppServiceException {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CategoryDTO categoryDTO = null;
        try {
            Optional<Category> category = categoryRepository.findById(id);
            categoryDTO = mapper.apply(category.get());
            httpStatus= HttpStatus.OK;

            return new ResponseEntity<CategoryDTO>(categoryDTO, httpStatus);
        } catch (Exception e) {
            throw new AppServiceException("ERROR OCCURED RETRIEVING CATEGORY DATA!!", 
                                e.getMessage(), 
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CategoryFilterDTO>> getAllCategoryNames(String module) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<CategoryFilterDTO> allCategoryNames = new ArrayList<>();
        try {
            List<String> allCategories = categoryRepository.findAllCategoryNames(module);
            allCategoryNames = categoryFilterMapper.applyForList(allCategories);
            httpStatus= HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<List<CategoryFilterDTO>>(allCategoryNames, httpStatus);
    }

    public ResponseEntity<List<CategoryDTO>> getCategoryTypeKeyByType(String categoryType) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<CategoryDTO> allCategoryNames = new ArrayList<>();
        try {
            List<Category> allCategories = categoryRepository.findCategoryTypeKeysByCategoryType(categoryType);
            allCategoryNames = mapper.applyForList(allCategories);
            httpStatus= HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<List<CategoryDTO>>(allCategoryNames, httpStatus);
    }

}
