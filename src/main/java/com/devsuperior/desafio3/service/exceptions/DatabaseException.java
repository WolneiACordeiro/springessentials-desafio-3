package com.devsuperior.desafio3.service.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg){
        super(msg);
    }
}
