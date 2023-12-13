package com.qualitapps.mangoapp.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.ModuleFilterDTO;
import com.qualitapps.mangoapp.entities.Module;

@Service
public class ModuleFilterMapper implements Function<Module, ModuleFilterDTO>{

    @Override
    public ModuleFilterDTO apply(Module module) {
        return new ModuleFilterDTO(
            module.getId(),
            module.getModule()
        );
    }

    public List<ModuleFilterDTO> applyFoList(List<Module> modules) {
        return modules.stream()
                .map((module) -> apply(module))
                .collect(Collectors.toList());
    }
    
}
