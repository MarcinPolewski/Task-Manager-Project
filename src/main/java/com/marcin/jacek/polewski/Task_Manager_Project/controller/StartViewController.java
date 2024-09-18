package com.marcin.jacek.polewski.Task_Manager_Project.controller;


import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ImageId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class StartViewController implements Initializable {
    @FXML
    private ImageView iconView;
    private MemoryHandler memoryHandler;

    @Autowired
    StartViewController(MemoryHandler memoryHandler)
    {
        this.memoryHandler = memoryHandler;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            iconView.setImage(memoryHandler.getImage(ImageId.APP_LOGO));
        } catch(IOException e)
        {
            System.out.println("failed to load image");
        }

    }
}
