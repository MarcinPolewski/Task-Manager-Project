package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TopBar extends HBox {
    private Button addTaskButton = new Button("New task");
    private WeatherPanel weatherPanel = new WeatherPanel();
    private TimePanel timePanel = new TimePanel();


    public TopBar()
    {
        super();
        loadComponents();
        this.getChildren().setAll(timePanel, weatherPanel, addTaskButton);
    }

    private void loadComponents()
    {

    }


}
