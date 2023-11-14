package com.qualitapps.mangoapp.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppServiceException  extends Exception{
    private String errorMessage;
    private String detailError;
    private HttpStatus httpStatus; 
}
