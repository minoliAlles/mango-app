package com.qualitapps.mangoapp.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.entities.Category;
import com.qualitapps.mangoapp.dto.CategoryFilterDTO;

@Service
public class CategoryFilterMapper implements Function<String, CategoryFilterDTO>{

    @Override
    public CategoryFilterDTO apply(String category) {
        return new CategoryFilterDTO(
           // category.getId() != null ?category.getId() :0 ,
           0,
            category
        );
    }

    public List<CategoryFilterDTO> applyForList(List<String> categoryTypes) {
        return categoryTypes.stream()
                .map((category) -> apply(category))
                .collect(Collectors.toList());
    }
    
}
