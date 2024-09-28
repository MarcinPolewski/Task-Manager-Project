package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //instantiate hour lines with labels assigned
        for(int hour=1; hour<= 24; ++hour)
        {
            scrollVBox.getChildren().add(new DayPreviewHourDisplay(hour));

//            Label hourLabel = new Label();
//            hourLabel.setText(String.valueOf(hour));
//
//            Line line = new Line();
//            hourLines.add(line);
//
//            // @TODO import this value from css !!! Injection?
//            double padding = 10.0;
//
//            // Bind the endX property of the Line to the width of the VBox
//            line.endXProperty().bind(scrollVBox.widthProperty().subtract(20));
//
//            VBox hourVBox = new VBox();
//            hourVBox.getChildren().setAll(hourLabel, line);
//            hourVBox.setPadding(new Insets(padding));
//
//            scrollVBox.getChildren().add(hourVBox);
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
