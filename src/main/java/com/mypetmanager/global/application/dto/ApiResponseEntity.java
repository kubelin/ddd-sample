package com.mypetmanager.global.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.xml.bind.annotation.XmlSchemaType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponseEntity<T> {

    
    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ApiResponseEntity(){

    }

    public ApiResponseEntity(String code, String message){
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ApiResponseEntity(String code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
