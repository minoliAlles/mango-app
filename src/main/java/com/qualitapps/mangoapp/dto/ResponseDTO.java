package com.qualitapps.mangoapp.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO implements Serializable {    
    private Integer status;
    private String path;
    private String version;
    private Object response;
    
}
