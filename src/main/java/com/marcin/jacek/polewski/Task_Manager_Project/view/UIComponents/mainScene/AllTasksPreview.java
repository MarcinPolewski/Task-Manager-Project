package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.controller.ControllerInterface;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryItem;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.AllTasksTreeView;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllTasksPreview extends VBox {

    private AllTasksTreeView treeView;


    public AllTasksPreview(TaskDirectoryService taskDirectoryService)
    {
        treeView = new AllTasksTreeView(taskDirectoryService, false);
        Label titleLabel = new Label("label");
        this.getChildren().setAll(titleLabel, treeView);

    }

    public void setOnAction(EventHandler<TaskPressedEvent> taskPressed, EventHandler<TaskDirectoryPressedEvent> directoryPressed)
    {
        treeView.setOnAction(taskPressed, directoryPressed);
    }

}
