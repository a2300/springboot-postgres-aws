package com.example.aws.springbootpostgresaws.exception;

public class FileArchiveServiceException extends RuntimeException{
    public FileArchiveServiceException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
