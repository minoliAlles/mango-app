package com.qualitapps.mangoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qualitapps.mangoapp.dto.MasterDataDTO;
import com.qualitapps.mangoapp.dto.ModuleDTO;
import com.qualitapps.mangoapp.dto.ResponseDTO;
import com.qualitapps.mangoapp.entities.MasterData;
import com.qualitapps.mangoapp.service.MasterDataService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/api/v1/masterData")
public class MasterDataController {

    @Autowired
    private MasterDataService masterDataService;

    @GetMapping("/getAllMasterData")
    public ResponseEntity<ResponseDTO> getAllMasterData() {
        
        ResponseEntity<List<MasterDataDTO>> allMasterData = masterDataService.getAllMasterData();
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getAllMasterData")
                .status(allMasterData.getStatusCode().value())
                .version("v1")
                .response(allMasterData.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, allMasterData.getStatusCode());
    }

    @PostMapping("/saveMasterData")
    public ResponseEntity<ResponseDTO> saveMasterData(@RequestBody MasterData masterData) {
        
        ResponseEntity<MasterData> newMasterDataEntry = masterDataService.saveMasterData(masterData);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/saveMasterData")
                .status(newMasterDataEntry.getStatusCode().value())
                .version("v1")
                .response(newMasterDataEntry.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, newMasterDataEntry.getStatusCode());
    }

    @PutMapping("/updateMasterData")
    public ResponseEntity<ResponseDTO> updateMasterData(@RequestBody MasterData masterData) {
        
        ResponseEntity<MasterData> updateMasterData = masterDataService.updateMasterData(masterData);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/updateMasterData")
                .status(updateMasterData.getStatusCode().value())
                .version("v1")
                .response(updateMasterData.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, updateMasterData.getStatusCode());
    }

    @DeleteMapping("/deleteMasterData")
    public ResponseEntity<ResponseDTO> deleteMasterData(@RequestParam("masterDataId") Integer masterDataId) {
        
        ResponseEntity<Boolean> isExist = masterDataService.deleteMasterData(masterDataId);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/deleteMasterData")
                .status(isExist.getStatusCode().value())
                .version("v1")
                .response(isExist.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, isExist.getStatusCode());
    }

    @GetMapping("/getMasterDataByKey")
    public ResponseEntity<ResponseDTO> getMasterDataByKey(@RequestParam String key) {
        
        ResponseEntity<List<MasterData>> allMasterData = masterDataService.getMasterDataByKey(key);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getMasterDataByKey")
                .status(allMasterData.getStatusCode().value())
                .version("v1")
                .response(allMasterData.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, allMasterData.getStatusCode());
    }

    @GetMapping("/getMasterDataByKeyAndType")
    public ResponseEntity<ResponseDTO> getMasterDataByKeyAndType(@RequestParam String type, @RequestParam String key) {
        
        ResponseEntity<List<MasterData>> allMasterData = masterDataService.getMasterDataByKeyAndType(type,key);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getMasterDataByKeyAndType")
                .status(allMasterData.getStatusCode().value())
                .version("v1")
                .response(allMasterData.getBody())
                .build();
            
        return new ResponseEntity<ResponseDTO>(responseDTO, allMasterData.getStatusCode());
    }

    @GetMapping("/getMasterDataById")
    public ResponseEntity<ResponseDTO> getMasterDataById(@RequestParam(value = "masterDataId") Integer id) {
        try {
            ResponseEntity<MasterDataDTO> module = masterDataService.getMasterDataById(id);
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getMasterDataById")
                .status(module.getStatusCode().value())
                .version("v1")
                .response(module.getBody())
                .build();
            
            return new ResponseEntity<ResponseDTO>(responseDTO, module.getStatusCode());
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getMasterDataById")
                .status(HttpStatus.BAD_REQUEST.value())
                .version("v1")
                .response(e.getMessage())
                .build();
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }        
    }
    
}
