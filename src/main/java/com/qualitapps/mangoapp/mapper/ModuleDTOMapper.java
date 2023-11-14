package com.qualitapps.mangoapp.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.ModuleDTO;
import com.qualitapps.mangoapp.entities.Module;

@Service
public class ModuleDTOMapper implements Function<Module, ModuleDTO>{

    @Override
    public ModuleDTO apply(Module module) {
        return new ModuleDTO(
            module.getId(), 
            module.getModule(),
            module.getCreateBy(),
            module.getCreatedDate(),
            module.getUpdatedBy(),
            module.getUpdatedDate(),
            module.getIsDeleted());
    }

    public List<ModuleDTO> applyForList(List<Module> allModules) {
        return allModules.stream()
                .map((module) -> apply(module))
                .collect(Collectors.toList());
    }
    
}
