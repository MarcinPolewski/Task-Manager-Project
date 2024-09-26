package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.image.ImageId;
import javafx.scene.control.Button;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class LoginUserButton extends Button {
    @Getter
    private User user;
    private MemoryHandler memoryHandler;
    public LoginUserButton(MemoryHandler memoryHandler, User user)
    {
        this.user = user;
        this.memoryHandler = memoryHandler;

        initializeUI();
    }

    private void initializeUI()
    {
        Image userImage;

        ImageView imageView = new ImageView();
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        Label usernameLabel = new Label(user.getUsername());
        try{
            userImage = memoryHandler.getImage(ImageId.USER_ICON);
            imageView.setImage(userImage);
        } catch (IOException e)
        {
            System.out.println("error occured during loading user image");
        }

        HBox hbox = new HBox();
        HBox.setHgrow(this, Priority.ALWAYS);
        this.setMaxWidth(Double.MAX_VALUE);
        hbox.getChildren().setAll(imageView, usernameLabel);

        setGraphic(hbox);
    }
}
