package com.qualitapps.mangoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qualitapps.mangoapp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{    
    
    @Query(value = "SELECT distinct category_type FROM category m WHERE m.is_deleted = false AND m.module = :module ", nativeQuery = true)
    List<String> findAllCategoryNames(String module);

    @Query(value = "SELECT * FROM category m WHERE category_type = :cType ", nativeQuery = true)
    List<Category> findCategoryTypeKeysByCategoryType(String cType);
}
