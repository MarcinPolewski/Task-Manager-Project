package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TimePanel extends VBox {
    TimePanel()
    {
        this.getChildren().setAll(new Label("this is time panel"),
                new Label("12:34"),
                new Label("12.05.2124"));
    }
}
