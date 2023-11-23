package com.qualitapps.mangoapp.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.CategoryDTO;
import com.qualitapps.mangoapp.entities.Category;

@Service
public class CategoryMapper implements Function<Category, CategoryDTO>{

    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
            category.getId(),
            category.getCategoryType(),
            category.getCategoryTypeKey(),
           // category.getCreatedBy(),
            // category.getCreatedDate(),
            // category.getUpdatedBy(),
            // category.getUpdatedDate(),
             category.getIsDeleted()
        );
    }

}
