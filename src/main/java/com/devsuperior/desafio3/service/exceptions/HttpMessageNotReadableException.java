package com.devsuperior.desafio3.service.exceptions;

public class HttpMessageNotReadableException extends RuntimeException {
    public HttpMessageNotReadableException(String msg){
        super(msg);
    }
}
