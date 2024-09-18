package com.marcin.jacek.polewski.Task_Manager_Project.controller;


import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ImageId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class StartViewController implements Initializable {
    @FXML
    private ImageView iconView;
    @FXML
    private Label greetingLabel;
    @FXML
    private Label mottoLabel;
    private MemoryHandler memoryHandler;
    private MessageSource messageSource;

    @Autowired
    StartViewController(MemoryHandler memoryHandler, MessageSource messageSource)
    {
        this.memoryHandler = memoryHandler;
        this.messageSource = messageSource;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // adding logo
        try{
            iconView.setImage(memoryHandler.getImage(ImageId.APP_LOGO));
        } catch(IOException e)
        {
            System.out.println("failed to load image");
        }
        // setting text on labels
        greetingLabel.setText(messageSource.getMessage("startScreenPrompt", null, "notFound", Locale.getDefault()));
        mottoLabel.setText(messageSource.getMessage("startScreenMotto", null, "notFound", Locale.getDefault()));
    }
}
