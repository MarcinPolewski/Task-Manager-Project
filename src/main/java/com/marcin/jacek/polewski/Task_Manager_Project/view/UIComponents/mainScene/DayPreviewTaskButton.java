package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Setter;

public class DayPreviewTaskButton extends Button {
    @Setter
    private EventHandler<TaskPressedEvent> taskPressedEventHandler;
    private Task task;

    DayPreviewTaskButton(Task task)
    {
        this.task = task;

        VBox vbox = new VBox();
        Label taskName = new Label(task.getTitle());
        Label creation = new Label("creation: " + task.getCreationData());
        Label due = new Label("due: " + task.getDueDate());

        vbox.getChildren().setAll(taskName, creation, due);
        this.setGraphic(vbox);

        this.setOnAction(this::handleButtonAction);
    }

    private void handleButtonAction(ActionEvent event)
    {
        if(taskPressedEventHandler!=null)
        {
            taskPressedEventHandler.handle(new TaskPressedEvent(task));
        }
    }


}
