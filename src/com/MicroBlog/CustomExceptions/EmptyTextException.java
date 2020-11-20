package com.MicroBlog.CustomExceptions;

public class EmptyTextException extends Exception{
    public EmptyTextException(String message){
        super(message);
    }
}
