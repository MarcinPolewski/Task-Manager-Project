package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.AllTasksTreeView;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class AllTasksPreview extends VBox {

    private AllTasksTreeView treeView;


    public AllTasksPreview(List<TaskDirectory> directories)
    {
        treeView = new AllTasksTreeView(directories, false);
        Label titleLabel = new Label("label");
        this.getChildren().setAll(titleLabel, treeView);
        this.getStyleClass().add("main-screen-background-element");

    }

    public void setOnAction(EventHandler<TaskPressedEvent> taskPressed, EventHandler<TaskDirectoryPressedEvent> directoryPressed)
    {
        treeView.setOnAction(taskPressed, directoryPressed);
    }

}
