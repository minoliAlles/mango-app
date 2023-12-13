package com.qualitapps.mangoapp.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.CategoryDTO;
import com.qualitapps.mangoapp.dto.CategoryFilterDTO;
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
             category.getIsDeleted(),
             category.getModule()
        );
    }

    public List<CategoryDTO> applyForList(List<Category> categories) {
        return categories.stream()
                .map((category) -> apply(category))
                .collect(Collectors.toList());
    }

}
