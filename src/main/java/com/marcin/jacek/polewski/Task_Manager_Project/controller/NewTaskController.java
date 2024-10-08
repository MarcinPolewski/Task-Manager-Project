package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.CannotGoBackError;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewTaskController {

    private ViewHandler viewHandler;

    @Autowired
    NewTaskController(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
    }

    public void cancelButtonPressed(ActionEvent event)
    {
        try{
            viewHandler.back();
        } catch(CannotGoBackError e)
        {
            System.out.println(e.getMessage());
        }
    }


}
