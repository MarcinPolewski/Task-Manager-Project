package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.model.TaskManagerApp;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.AllTasksPreview;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.TasksOfTheDayPreview;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.TopBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Component
public class MainViewController implements Initializable, TopBarController {
    @FXML
    BorderPane mainBorderPane;

    @FXML
    private HBox centerHBox;

    @FXML
    StackPane mainStackPane;

    private TaskManagerApp taskManagerApp;
    private TaskDirectoryService taskDirectoryService;

    @Autowired
    MainViewController(TaskManagerApp taskManagerApp, TaskDirectoryService taskDirectoryService)
    {
        this.taskManagerApp = taskManagerApp;
        this.taskDirectoryService = taskDirectoryService;
    }


    private void initializeTopBar()
    {
        mainBorderPane.setTop(new TopBar(this));
    }
    private void initializeDayTaskPreview()
    {
        TaskManager tm = taskManagerApp.getCurrentUser().getTaskManager();

        TasksOfTheDayPreview dayPreview = new TasksOfTheDayPreview(this, LocalDateTime.now(), tm);
        centerHBox.getChildren().add(dayPreview);
    }

    private void initializeAllTasksPreview()
    {
        AllTasksPreview taskPreview = new AllTasksPreview(this, taskDirectoryService);
        centerHBox.getChildren().add(taskPreview);
    }

    @Override
    public void newTaskButtonPressed(ActionEvent event)
    {
        // @TODO implement method
        System.out.println("create new task");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTopBar();
        initializeDayTaskPreview();
        initializeAllTasksPreview();
    }
}
