package com.qualitapps.mangoapp.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.MasterDataDTO;
import com.qualitapps.mangoapp.entities.MasterData;
import com.qualitapps.mangoapp.repository.MasterDataRepository;
import com.qualitapps.mangoapp.repository.ModuleRepository;

@Service
public class MasterDataMapper implements Function<MasterData, MasterDataDTO>{

    @Autowired
    private ModuleRepository repository;

    @Override
    public MasterDataDTO apply(MasterData mData) {


        return new MasterDataDTO(
            mData.getId(),
            mData.getModuleId(),
            mData.getCategoryType(),
            mData.getCategoryTypeKey(),
            mData.getData(),
            mData.getTranslation(),
            mData.getIsDeleted(),
            repository.findById(mData.getModuleId()).get().getModule()
        );
    }

    public List<MasterDataDTO> applyForList(List<MasterData> allData) {
        List<MasterDataDTO> collect = allData.stream().map(d -> apply(d)).collect(Collectors.toList());
        return collect;
    }

}
