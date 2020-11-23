package com.MicroBlog.CustomExceptions;

public class BadWordException extends Exception{
    public BadWordException(String message){
        super(message);
    }
}
