package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class LogInViewController implements Initializable{
    @FXML
    private Label promptLabel;
    @FXML
    private Label signUpLabel;
    @FXML
    private Button editUserListButton;
    @FXML
    private Button addUserToListButton;

    private MessageSource messageSource;

    @Autowired
    LogInViewController(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // add text to labels
        promptLabel.setText(messageSource.getMessage("logInScreenSelectUser",null, "notFound", Locale.getDefault()));
        signUpLabel.setText(messageSource.getMessage("logInScreenClickToSignUp",null, "notFound", Locale.getDefault()));
    }
}
