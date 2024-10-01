package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TasksOfTheDayPreview extends VBox {
    private HBox topBarHBox = new HBox();
    private Button previousDay= new Button("<");
    private Button nextDay = new Button(">");
    private Label dayLabel = new Label();
    private List<Line>hourLines = new ArrayList<>();

    private void setUpTopBar(LocalDateTime currentTime)
    {
        dayLabel.setText(currentTime.toString());
        topBarHBox.getChildren().setAll(previousDay, dayLabel, nextDay);
        topBarHBox.setFillHeight(true);

        // add top bar to this component
        this.getChildren().add(topBarHBox);
    }

    private void setUpHourScrollView(LocalDateTime currentTime, TaskManager taskManager)
    {
        // day view setting
        VBox scrollVBox = new VBox();
        // Bind the VBox's width to the width of the ScrollPane's viewport
        scrollVBox.setFillWidth(true);


        ScrollPane scroll = new ScrollPane(scrollVBox);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //instantiate hour lines with labels assigned
        for(int hour=1; hour<= 24; ++hour)
        {
            DayPreviewHourDisplay hourDisplay = new DayPreviewHourDisplay(hour);
            scrollVBox.getChildren().add(hourDisplay);

            // @TODO delete, only for ui testing purposes
            hourDisplay.addTask(taskManager.getTasks().get(0));
            hourDisplay.addTask(taskManager.getTasks().get(1));
        }

        this.getChildren().add(scroll);
    }

    public TasksOfTheDayPreview(LocalDateTime currentTime, TaskManager taskManager)
    {
        super();
        setUpTopBar(currentTime);
        setUpHourScrollView( currentTime,  taskManager);
    }

//    private void addTaskBlock(Task task)
//    {
//
//    }

}
