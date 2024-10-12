package com.marcin.jacek.polewski.Task_Manager_Project.exceptions;

import lombok.Getter;

public class InvalidUserInputException extends RuntimeException{
    @Getter
    private String userPrompt;
    public InvalidUserInputException(String s)
    {
        super(s);
    }
    public InvalidUserInputException(String s, String userPrompt)
    {
        super(s);
        this.userPrompt = userPrompt;
    }

}
