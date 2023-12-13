package com.qualitapps.mangoapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;	
    private String categoryType;
	private String categoryTypeKey;
    // private String createdBy;
    // private Date createdDate;
    // private String updatedBy;
    // private Date updatedDate;
    private Boolean isDeleted;
    private String module;
}
