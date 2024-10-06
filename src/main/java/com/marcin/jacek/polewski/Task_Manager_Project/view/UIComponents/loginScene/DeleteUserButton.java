package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.loginScene;


import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import javafx.scene.control.Button;
import lombok.Getter;

public class DeleteUserButton extends Button {

    @Getter
    private User user;

    DeleteUserButton(User user)
    {
        this.setText("-");
        this.user = user;
    }
}
