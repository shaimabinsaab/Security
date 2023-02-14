package com.example.blog_system.Advice;

public class ApiException extends RuntimeException {
    public ApiException(String msg){
        super(msg);
    }
}
