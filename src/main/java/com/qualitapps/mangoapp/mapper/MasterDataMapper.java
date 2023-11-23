package com.qualitapps.mangoapp.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.qualitapps.mangoapp.dto.MasterDataDTO;
import com.qualitapps.mangoapp.entities.MasterData;

@Service
public class MasterDataMapper implements Function<MasterData, MasterDataDTO>{

    @Override
    public MasterDataDTO apply(MasterData mData) {
        return new MasterDataDTO(
            mData.getId(),
            mData.getModuleId(),
            mData.getCategoryType(),
            mData.getCategoryTypeKey(),
            mData.getData(),
            mData.getTranslation(),
            mData.getIsDeleted()
        );
    }

}
