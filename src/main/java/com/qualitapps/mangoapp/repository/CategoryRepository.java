package com.qualitapps.mangoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qualitapps.mangoapp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
