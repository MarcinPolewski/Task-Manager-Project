package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.topBar;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WeatherPanel extends VBox {
    WeatherPanel()
    {
        this.getChildren().setAll(new Label("This is the weather"));
    }

}
