package com.qualitapps.mangoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qualitapps.mangoapp.entities.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer>{

    @Query(value = "SELECT * FROM module m " +
            "WHERE m.is_deleted = false ", nativeQuery = true)
    List<Module> findAllActiveModules();
    
}
