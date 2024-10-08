package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.topBar;

import com.marcin.jacek.polewski.Task_Manager_Project.util.ClockProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.Clock;
import java.time.LocalDateTime;

public class TimePanel extends VBox {
    private Label timeLabel = new Label();
    private ClockProperty clockProperty= new ClockProperty();

    TimePanel()
    {
        timeLabel.textProperty().bind(clockProperty);
        this.getChildren().setAll(timeLabel);
    }
}
