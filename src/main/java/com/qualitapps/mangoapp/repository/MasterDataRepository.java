package com.qualitapps.mangoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qualitapps.mangoapp.entities.MasterData;

public interface MasterDataRepository extends JpaRepository<MasterData, Integer>{

    List<MasterData> findAllByCategoryTypeKey(String category_type_key);

    @Query(value = "SELECT * FROM master_data m " +
            "WHERE m.category_type = :type AND " +
            "ucase(m.category_type_key) like ucase(concat('%', :key))", nativeQuery = true)
    List<MasterData> findAllByCategoryKeyAndType(String type, String key);
    
}
