package com.qualitapps.mangoapp.mapper;

import com.google.gson.Gson;
import com.qualitapps.mangoapp.entities.TranslationData;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TranslationMapper  implements AttributeConverter<TranslationData, String>{

    private final static Gson GSON = new Gson();

    @Override
    public String convertToDatabaseColumn(TranslationData translationData) {
        return GSON.toJson(translationData);
    }

    @Override
    public TranslationData convertToEntityAttribute(String dbData) {
        return GSON.fromJson(dbData, TranslationData.class);
    }
    
}
