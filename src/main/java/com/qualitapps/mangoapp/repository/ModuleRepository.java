package com.qualitapps.mangoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qualitapps.mangoapp.entities.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer>{
    
}
