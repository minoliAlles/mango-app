package com.qualitapps.mangoapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {    
    private Integer id;
    private String module;
    private String createBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private Boolean isDeleted;
}
