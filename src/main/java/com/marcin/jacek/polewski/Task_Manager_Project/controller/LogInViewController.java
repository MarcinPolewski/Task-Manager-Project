package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
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
    private UserService userService;

    @Autowired
    LogInViewController(MessageSource messageSource, UserService userService)
    {
        this.messageSource = messageSource;
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // add text to labels
        promptLabel.setText(messageSource.getMessage("logInScreenSelectUser",null, "notFound", Locale.getDefault()));
        signUpLabel.setText(messageSource.getMessage("logInScreenClickToSignUp",null, "notFound", Locale.getDefault()));

//        List<User> users = userService.findAll();
//        for(User user : users)
//        {
//            System.out.println(user.getUsername());
//        }
    }
}
