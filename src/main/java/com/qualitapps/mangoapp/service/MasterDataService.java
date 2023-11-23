package com.qualitapps.mangoapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.MasterDataDTO;
import com.qualitapps.mangoapp.dto.ModuleDTO;
import com.qualitapps.mangoapp.entities.MasterData;
import com.qualitapps.mangoapp.exception.AppServiceException;
import com.qualitapps.mangoapp.mapper.MasterDataMapper;
import com.qualitapps.mangoapp.repository.MasterDataRepository;

@Service
public class MasterDataService {

    @Autowired
    private MasterDataRepository repository;

    @Autowired
    private MasterDataMapper mapper;

    public ResponseEntity<List<MasterData>> getAllMasterData() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<MasterData> allData = new ArrayList<>();
        try {
            allData = repository.findAll();
            httpStatus= HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<List<MasterData>>(allData, httpStatus);
    }

    public ResponseEntity<MasterData> saveMasterData(MasterData masterData) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            masterData.setCreatedDate(new Date());
            masterData.setIsDeleted(false);
            masterData = repository.save(masterData);
            httpStatus= HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<MasterData>(masterData, httpStatus);
    }

    public ResponseEntity<MasterData> updateMasterData(MasterData masterData) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
        try {
           Optional<MasterData> updateData = repository.findById(masterData.getId());
           if(updateData.isPresent()){
                updateData.get().setUpdatedBy("Admin");
                updateData.get().setUpdatedDate(new Date());
                updateData.get().setData(masterData.getData());
                masterData = repository.saveAndFlush(updateData.get());
                httpStatus= HttpStatus.OK;
           }

           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<MasterData>(masterData,httpStatus);
    }

    public ResponseEntity<Boolean> deleteMasterData(Integer masterDataId) {
        Boolean isDelete = false;
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            Optional<MasterData> masterData = repository.findById(masterDataId);
            if(masterData.isPresent()){
                masterData.get().setIsDeleted(true);
                repository.saveAndFlush(masterData.get());
                isDelete = true;
                httpStatus = HttpStatus.OK;
            }            
        } catch (Exception e) {
           e.printStackTrace();
        }
        return new ResponseEntity<Boolean>(isDelete,httpStatus);
    }

    public ResponseEntity<List<MasterData>> getMasterDataByKey(String key) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<MasterData> allData = new ArrayList<>();
        try {
            allData = repository.findAllByCategoryTypeKey(key);
            httpStatus= HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<List<MasterData>>(allData, httpStatus);
    }

    public ResponseEntity<List<MasterData>> getMasterDataByKeyAndType(String type, String key) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<MasterData> allData = new ArrayList<>();
        try {
            allData = repository.findAllByCategoryKeyAndType(type, key);
            httpStatus= HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<List<MasterData>>(allData, httpStatus);
    }

    public ResponseEntity<MasterDataDTO> getMasterDataById(Integer id) throws AppServiceException {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        MasterDataDTO masterDataDTO = null;
        try {
            Optional<MasterData> masterData = repository.findById(id);
            masterDataDTO = mapper.apply(masterData.get());
            httpStatus= HttpStatus.OK;

            return new ResponseEntity<MasterDataDTO>(masterDataDTO, httpStatus);
        } catch (Exception e) {
            throw new AppServiceException("ERROR OCCURED RETRIEVING MODULE DATA!!", 
                                e.getMessage(), 
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
