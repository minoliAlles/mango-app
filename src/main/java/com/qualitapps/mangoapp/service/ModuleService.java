package com.qualitapps.mangoapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.ModuleDTO;
import com.qualitapps.mangoapp.entities.Module;
import com.qualitapps.mangoapp.exception.AppServiceException;
import com.qualitapps.mangoapp.mapper.ModuleDTOMapper;
import com.qualitapps.mangoapp.repository.ModuleRepository;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleDTOMapper mapper;

    public ResponseEntity<List<ModuleDTO>> getAllModules() throws AppServiceException {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<ModuleDTO> modulesDto = new ArrayList<>();
        try {
            List<Module> allModules = moduleRepository.findAll();
            modulesDto = mapper.applyForList(allModules);
            httpStatus= HttpStatus.OK;

            return new ResponseEntity<List<ModuleDTO>>(modulesDto, httpStatus);
        } catch (Exception e) {
            throw new AppServiceException("ERROR OCCURED RETRIEVING MODULE DATA!!", 
                                e.getMessage(), 
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }    
    }

    public ResponseEntity<ModuleDTO> saveModule(Module module) throws AppServiceException {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            module.setCreatedDate(new Date());
            module.setIsDeleted(false);
            module = moduleRepository.save(module);
            ModuleDTO moduleDTO = mapper.apply(module);
            httpStatus= HttpStatus.OK;

            return new ResponseEntity<ModuleDTO>(moduleDTO, httpStatus);
        } catch (Exception e) {
            throw new AppServiceException("ERROR OCCURED SAVING MODULE!!", 
                                e.getMessage(), 
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    public ResponseEntity<ModuleDTO> updateModule(ModuleDTO moduleDTO) throws AppServiceException {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;        
        try {
           Optional<Module> updateModule = moduleRepository.findById(moduleDTO.getId());
           if(updateModule.isPresent()){
                updateModule.get().setUpdatedBy("Admin");
                updateModule.get().setUpdatedDate(new Date());
                updateModule.get().setModule(moduleDTO.getModule());
                moduleDTO = mapper.apply(moduleRepository.saveAndFlush(updateModule.get()));
                httpStatus= HttpStatus.OK;
           }else{
                throw new Exception();
           }           
        } catch (Exception e) {            
            throw new AppServiceException("ERROR OCCURED UPDATING MODULE!!", 
                                e.getMessage(), 
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ModuleDTO>(moduleDTO,httpStatus);
    }

    public ResponseEntity<Boolean> deleteModule(Integer moduleId) throws AppServiceException {
        Boolean isDelete = false;
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            Optional<Module> module = moduleRepository.findById(moduleId);
            if(module.isPresent()){
                module.get().setIsDeleted(true);
                moduleRepository.saveAndFlush(module.get());
                isDelete = true;
                httpStatus = HttpStatus.OK;
            }            
        } catch (Exception e) {
           throw new AppServiceException("ERROR OCCURED DELETING MODULE!!", 
                                e.getMessage(), 
                                HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Boolean>(isDelete,httpStatus);
    }   

}
