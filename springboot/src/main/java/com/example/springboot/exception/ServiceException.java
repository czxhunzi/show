package com.example.springboot.exception;

import cn.hutool.core.lang.copier.SrcToDestCopier;

public class ServiceException extends RuntimeException{
    private String code;

    public String getCode(){
        return code;
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String code, String message){
        super(message); this.code=code;
    }

}
