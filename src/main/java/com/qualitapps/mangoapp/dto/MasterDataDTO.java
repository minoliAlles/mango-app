package com.qualitapps.mangoapp.dto;

import java.util.Date;

import com.qualitapps.mangoapp.entities.TranslationData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterDataDTO {
    private Integer id;
    private Integer moduleId;

	private String categoryType;
	private String categoryTypeKey;
	private String data;

    private TranslationData translation;
    // private Date createdDate;
    // private String updatedBy;
    // private Date updatedDate;
    private Boolean isDeleted;
    private String moduleName;
}
