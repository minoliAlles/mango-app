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

import com.qualitapps.mangoapp.service.ModuleService;
import com.qualitapps.mangoapp.dto.ModuleDTO;
import com.qualitapps.mangoapp.dto.ModuleFilterDTO;
import com.qualitapps.mangoapp.dto.ResponseDTO;
import com.qualitapps.mangoapp.entities.Module;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/module")
public class ModuleController {   

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/getAllModules")
    public ResponseEntity<ResponseDTO> getAllModules() {
        try {
            ResponseEntity<List<ModuleDTO>> allModules = moduleService.getAllModules();
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getAllModules")
                .status(allModules.getStatusCode().value())
                .version("v1")
                .response(allModules.getBody())
                .build();
            
            return new ResponseEntity<ResponseDTO>(responseDTO, allModules.getStatusCode());
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getAllModules")
                .status(HttpStatus.BAD_REQUEST.value())
                .version("v1")
                .response(e.getMessage())
                .build();
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }        
    }

    @PostMapping("/saveModule")
    public ResponseEntity<ResponseDTO> saveModule(@RequestBody Module module) {
        try {
            ResponseEntity<ModuleDTO> newUser = moduleService.saveModule(module);
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/saveModule")
                .status(newUser.getStatusCode().value())
                .version("v1")
                .response(newUser.getBody())
                .build();
            
            return new ResponseEntity<ResponseDTO>(responseDTO, newUser.getStatusCode());
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/saveModule")
                .status(HttpStatus.BAD_REQUEST.value())
                .version("v1")
                .response(e.getMessage())
                .build();
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateModule")
    public ResponseEntity<ResponseDTO> updateModule(@RequestBody ModuleDTO moduleDTO) {
        try {
            ResponseEntity<ModuleDTO> newUser = moduleService.updateModule(moduleDTO);
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/updateModule")
                .status(newUser.getStatusCode().value())
                .version("v1")
                .response(newUser.getBody())
                .build();
            
            return new ResponseEntity<ResponseDTO>(responseDTO, newUser.getStatusCode());
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/updateModule")
                .status(HttpStatus.BAD_REQUEST.value())
                .version("v1")
                .response(e.getLocalizedMessage())
                .build();
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }        
    }

    @DeleteMapping("/deleteModule")
    public ResponseEntity<ResponseDTO> deleteModule(@RequestParam(value="moduleId") Integer moduleId) {
        try {
            ResponseEntity<Boolean> userExist = moduleService.deleteModule(moduleId);
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/deleteModule")
                .status(userExist.getStatusCode().value())
                .version("v1")
                .response(userExist.getBody())
                .build();
            
            return new ResponseEntity<ResponseDTO>(responseDTO, userExist.getStatusCode());
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/deleteModule")
                .status(HttpStatus.BAD_REQUEST.value())
                .version("v1")
                .response(e.getMessage())
                .build();
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getModuleById")
    public ResponseEntity<ResponseDTO> getModuleById(@RequestParam(value = "moduleId") Integer id) {
        try {
            ResponseEntity<ModuleDTO> module = moduleService.getModuleById(id);
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getModuleById")
                .status(module.getStatusCode().value())
                .version("v1")
                .response(module.getBody())
                .build();
            
            return new ResponseEntity<ResponseDTO>(responseDTO, module.getStatusCode());
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getModuleById")
                .status(HttpStatus.BAD_REQUEST.value())
                .version("v1")
                .response(e.getMessage())
                .build();
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }        
    }

    @GetMapping("/getAllModuleNames")
    public ResponseEntity<ResponseDTO> getAllModuleNames() {
        try {
            ResponseEntity<List<ModuleFilterDTO>> allModules = moduleService.getAllModuleIdsAndNames();
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getAllModuleNames")
                .status(allModules.getStatusCode().value())
                .version("v1")
                .response(allModules.getBody())
                .build();
            
            return new ResponseEntity<ResponseDTO>(responseDTO, allModules.getStatusCode());
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                .path("/getAllModuleNames")
                .status(HttpStatus.BAD_REQUEST.value())
                .version("v1")
                .response(e.getMessage())
                .build();
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }        
    }
}
