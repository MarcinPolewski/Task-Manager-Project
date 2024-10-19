package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.AllTasksTreeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class AllTasksPreview extends VBox {

    private AllTasksTreeView treeView;
    private HBox topBarHBox = new HBox();
    private Button newFolderButton = new Button("+");
    public AllTasksPreview(List<TaskDirectory> directories)
    {

        Label titleLabel = new Label("label");
        topBarHBox.getChildren().setAll(titleLabel, newFolderButton);

        treeView = new AllTasksTreeView(directories, false);
        this.getChildren().setAll(topBarHBox, treeView);
        this.getStyleClass().add("main-screen-background-element");

    }

    public void setOnAction(EventHandler<TaskPressedEvent> taskPressed,
                            EventHandler<TaskDirectoryPressedEvent> directoryPressed,
                            EventHandler<ActionEvent> newFolderPressed)
    {
        treeView.setOnAction(taskPressed, directoryPressed);
        newFolderButton.setOnAction(newFolderPressed);

    }

    public void addFolder(TaskDirectory newDirectory)
    {
        treeView.addDirectoryToTree(newDirectory);
    }

}
