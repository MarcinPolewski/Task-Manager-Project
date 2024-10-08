package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.image.ImageId;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SideBar extends VBox {
    Button homeButton = new Button();
    Button usersButton = new Button();
    Button settingButton = new Button();
    Button statisticsButton = new Button();

    private void initializeButtons(MemoryHandler memoryHandler) throws IOException
    {
        homeButton.setGraphic(new ImageView(memoryHandler.getImage(ImageId.HOME_ICON)));
        usersButton.setGraphic(new ImageView(memoryHandler.getImage(ImageId.USERS_ICON)));
        settingButton.setGraphic(new ImageView(memoryHandler.getImage(ImageId.SETTINGS_ICON)));
        statisticsButton.setGraphic(new ImageView(memoryHandler.getImage(ImageId.CHART_ICON)));

        this.getChildren().setAll(homeButton, statisticsButton, usersButton, settingButton);
    }

    public SideBar(MemoryHandler memoryHandler)
    {
        try{
            initializeButtons(memoryHandler);
        } catch (IOException e)
        {
            System.out.println("could not read icons for side bar");
        }
    }
}
