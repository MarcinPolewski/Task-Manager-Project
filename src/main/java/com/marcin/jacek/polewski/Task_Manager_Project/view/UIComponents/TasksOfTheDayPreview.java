package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.time.LocalDateTime;

public class TasksOfTheDayPreview extends VBox {
    private HBox topBarHBox = new HBox();
    private Button previousDay= new Button("<");
    private Button nextDay = new Button(">");
    private Label dayLabel = new Label();

    public TasksOfTheDayPreview(LocalDateTime currentTime, TaskManager taskManager)
    {
        super();
        // top bar set up
        dayLabel.setText(currentTime.toString());
        topBarHBox.getChildren().setAll(previousDay, dayLabel, nextDay);

        // day view setting
        VBox scrollVBox = new VBox();
        // Bind the VBox's width to the width of the ScrollPane's viewport
        scrollVBox.setFillWidth(true);

        for(int hour=1; hour<= 24; ++hour)
        {
            Label hourLabel = new Label();
            hourLabel.setText(String.valueOf(hour));

            Line line = new Line();
            // Bind the endX property of the Line to the width of the VBox
            line.endXProperty().bind(scrollVBox.widthProperty());

            VBox hourVBox = new VBox();
            hourVBox.getChildren().setAll(hourLabel, line);


            scrollVBox.getChildren().add(hourVBox);
        }

        ScrollPane scroll = new ScrollPane(scrollVBox);

        this.getChildren().setAll(topBarHBox, scroll);

    }

//    private void addTaskBlock(Task task)
//    {
//
//    }

}
