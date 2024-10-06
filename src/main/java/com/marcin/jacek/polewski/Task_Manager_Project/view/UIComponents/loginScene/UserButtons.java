package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.loginScene;

import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import lombok.Getter;

public class UserButtons extends HBox {
    private LoginUserButton loginUserButton;
    private DeleteUserButton deleteUserButton;

    @Getter
    private boolean editable = false;

    @Getter
    private User user;

    public UserButtons(MemoryHandler memoryHandler, User user)
    {
        this.user = user;

        loginUserButton = new LoginUserButton(memoryHandler, user);
        deleteUserButton = new DeleteUserButton(user);
        if(editable)
            enableEditMode();
        else
            disableEditMode();
    }

    private void enableEditMode()
    {
        this.getChildren().setAll(loginUserButton, deleteUserButton);
    }

    private void disableEditMode()
    {
        this.getChildren().setAll(loginUserButton);
    }


    public void setOnAction(EventHandler<ActionEvent> logInUser,
                            EventHandler<ActionEvent> deleteUser)
    {
        loginUserButton.setOnAction(logInUser);
        deleteUserButton.setOnAction(deleteUser);
    }


    public void setEditable(boolean newEditMode)
    {
        if(editable != newEditMode)
        {
            if(newEditMode)
                enableEditMode();
            else
                disableEditMode();
            editable = newEditMode;
        }
    }
}
