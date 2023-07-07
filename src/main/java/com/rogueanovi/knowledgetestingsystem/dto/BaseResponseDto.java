package com.rogueanovi.knowledgetestingsystem.dto;

import lombok.Data;

@Data
public class BaseResponseDto {
    private Boolean isSuccessful;
    private String message;
    private Object data;
    private String error;
}
