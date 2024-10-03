package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.controller.ControllerInterface;
import com.marcin.jacek.polewski.Task_Manager_Project.controller.TopBarController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TopBar extends HBox {
    private Button addTaskButton = new Button("New task");
    private WeatherPanel weatherPanel = new WeatherPanel();
    private TimePanel timePanel = new TimePanel();
    private TopBarController controller;


    public TopBar(TopBarController controller)
    {
        super();
        this.controller = controller;
        initializeComponents();
        this.getChildren().setAll(timePanel, weatherPanel, addTaskButton);


    }

    private void initializeComponents()
    {
        addTaskButton.setOnAction(controller::newTaskButtonPressed);
    }


}
