package com.marcin.jacek.polewski.Task_Manager_Project.exceptions;

public class InvalidTaskDate extends RuntimeException{
    public InvalidTaskDate(String s)
    {
        super(s);
    }
}
