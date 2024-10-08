package com.marcin.jacek.polewski.Task_Manager_Project.exceptions;

public class CannotGoBackError extends RuntimeException{
    public CannotGoBackError()
    {
        super("cannot go back, because scene stack is empty");
    }
}
