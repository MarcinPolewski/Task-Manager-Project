package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class DayPreviewHourDisplay extends VBox {
    private Line line = new Line();
    private Label hourLabel = new Label();
    private HBox labelAndTasksHBox = new HBox();
    private HBox tasksHBox = new HBox();

    private EventHandler<TaskPressedEvent> taskPressedEventHandler;

    public void clear()
    {
        tasksHBox.getChildren().clear();
    }

    DayPreviewHourDisplay(int hour)
    {
        super();

        this.setFillWidth(true);

        hourLabel.setText(String.valueOf(hour));
        labelAndTasksHBox.getChildren().setAll(hourLabel, tasksHBox);
        labelAndTasksHBox.setAlignment(Pos.BOTTOM_CENTER);


        // padding must be greater than one pixel, therefore it below binding
        // does not cause container t o indefinitely stretch right
        double rightPadding = this.getPadding().getRight();
        if(rightPadding < 1)
        {
            rightPadding = 1;
        }

        line.endXProperty().bind(this.widthProperty()
                .subtract(rightPadding));


        this.getChildren().setAll(labelAndTasksHBox, line);
    }

    public void setOnAction(EventHandler<TaskPressedEvent> taskPressedEventHandler)
    {
        this.taskPressedEventHandler = taskPressedEventHandler;
        for(Node button : tasksHBox.getChildren())
        {
            if(button instanceof  DayPreviewTaskButton)
            {
                ((DayPreviewTaskButton)button).setTaskPressedEventHandler(taskPressedEventHandler);
            }
        }
    }



    public void addTask(Task task)
    {
        DayPreviewTaskButton button = new DayPreviewTaskButton(task);
        button.setTaskPressedEventHandler(taskPressedEventHandler);
        tasksHBox.getChildren().add(button);
    }

}
