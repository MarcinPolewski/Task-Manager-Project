package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.taskViewBase;

import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.TaskState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Setter;

import java.util.Arrays;


public class SubTaskPanel extends HBox {
    private Button deleteSubTaskButton;
    private SubTaskChangeStatusButton<TaskState> subTaskChangeStatusButton;
    private Label titleLabel;

    private SubTask subTask;
    @Setter
    private EventHandler<ActionEvent> onTaskDelete;

    public SubTaskPanel(SubTask subTask)
    {
        super();
        this.subTask = subTask;

        initializeLabel();
        initializeButtons();
    }

    private void initializeLabel()
    {
        this.titleLabel = new Label(this.subTask.getTitle());
        this.getChildren().add(titleLabel);
    }
    private void initializeButtons()
    {

        subTaskChangeStatusButton = new SubTaskChangeStatusButton<>(this.subTask, Arrays.stream(TaskState.values()).toList(), this.subTask.getState().ordinal());
        subTaskChangeStatusButton.setOnPressed(this::stateButtonPressed);
        this.getChildren().add(subTaskChangeStatusButton);


        deleteSubTaskButton = new Button("-");
        deleteSubTaskButton.setOnAction(this::deleteButtonPressed);
        this.getChildren().add(deleteSubTaskButton);
    }

    private void stateButtonPressed(ActionEvent event)
    {
        subTask.setState(subTaskChangeStatusButton.getValue());
    }

    private void deleteButtonPressed(ActionEvent event)
    {
        onTaskDelete.handle(event);
    }




}
