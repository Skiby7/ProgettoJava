package com.MicroBlog.CustomExceptions;
public class IllegalLengthException extends Exception{
    public IllegalLengthException(String message){
        super(message);
    }
}