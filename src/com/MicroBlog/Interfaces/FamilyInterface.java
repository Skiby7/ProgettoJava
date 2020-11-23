package com.MicroBlog.Interfaces;

import com.MicroBlog.CustomExceptions.BadWordException;
import com.MicroBlog.CustomExceptions.EmptyTextException;
import com.MicroBlog.CustomExceptions.IllegalLengthException;

public interface FamilyInterface extends SocialInterface{
    @Override
    void addPost(String user, String text) throws IllegalLengthException, EmptyTextException, NullPointerException;
}
