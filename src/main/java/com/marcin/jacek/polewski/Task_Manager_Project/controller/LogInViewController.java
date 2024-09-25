package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.UserService;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.LoginUserButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
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

    @FXML
    private VBox vboxWithUsers;

    private MessageSource messageSource;
    private UserService userService;
    private MemoryHandler memoryHandler;

    @Autowired
    LogInViewController(MemoryHandler memoryHandler, MessageSource messageSource, UserService userService)
    {
        this.memoryHandler = memoryHandler;
        this.messageSource = messageSource;
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // add text to labels
        promptLabel.setText(messageSource.getMessage("logInScreenSelectUser",null, "notFound", Locale.getDefault()));
        signUpLabel.setText(messageSource.getMessage("logInScreenClickToSignUp",null, "notFound", Locale.getDefault()));

        List<User> users = userService.findAll();
        for(User user : users)
        {
            vboxWithUsers.getChildren().add(new LoginUserButton(memoryHandler, user));
        }
    }
}
