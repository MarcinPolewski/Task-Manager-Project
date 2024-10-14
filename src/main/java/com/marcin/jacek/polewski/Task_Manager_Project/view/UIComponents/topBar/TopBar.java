package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.topBar;

import com.marcin.jacek.polewski.Task_Manager_Project.controller.ControllerInterface;
import com.marcin.jacek.polewski.Task_Manager_Project.controller.MainViewController;
import com.marcin.jacek.polewski.Task_Manager_Project.controller.TopBarController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TopBar extends HBox {
    private TimePanel timePanel = new TimePanel();


    public TopBar()
    {
        super();
        this.getChildren().setAll(timePanel);
    }

}
