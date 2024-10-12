package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.model.TaskManagerApp;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.SideBar;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene.AllTasksPreview;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene.TasksOfTheDayPreview;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.topBar.TopBar;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainViewController implements Initializable, ControllerInterface {
    @FXML
    BorderPane mainBorderPane;

    @FXML
    private HBox centerHBox;

    @FXML
    private HBox topBarHBox;

    @FXML
    StackPane mainStackPane;

    private TaskManagerApp taskManagerApp;
    private ViewHandler viewHandler;
    private MemoryHandler memoryHandler;

    @Autowired
    MainViewController(TaskManagerApp taskManagerApp,
                       ViewHandler viewHandler,
                       MemoryHandler memoryHandler)
    {
        this.taskManagerApp = taskManagerApp;
        this.viewHandler = viewHandler;
        this.memoryHandler = memoryHandler;
    }


    private void initializeTopBar()
    {

        topBarHBox.getChildren().setAll(new TopBar(this));
        topBarHBox.setFillHeight(false);
        topBarHBox.setAlignment(javafx.geometry.Pos.CENTER);
    }


    private void taskPressed(TaskPressedEvent taskPressedEvent)
    {
        viewHandler.openTaskView(taskPressedEvent.getTask());
    }

    private void directoryPressed(TaskDirectoryPressedEvent directoryPressedEvent)
    {
        viewHandler.openDirectoryView(directoryPressedEvent.getTaskDirectory());
    }

    private void homePressed(ActionEvent event)
    {
        System.out.println("open home screen");
    }
    public void statisticsPressed(ActionEvent event)
    {
        System.out.println("open statistics view");
    }

    public void usersPressed(ActionEvent event)
    {
        taskManagerApp.logOutUser();
        viewHandler.switchToLogInScene();
    }

    public void settingsPressed(ActionEvent event)
    {
        System.out.println("open settings view");
    }


    private void initializeCenterScreen()
    {
        List<TaskDirectory> directories = taskManagerApp.getCurrentUser().getTaskManager().getTaskDirectories();
        AllTasksPreview taskPreview = new AllTasksPreview(directories);

        taskPreview.setOnAction(this::taskPressed, this::directoryPressed);

        TaskManager tm = taskManagerApp.getCurrentUser().getTaskManager();

        TasksOfTheDayPreview dayPreview = new TasksOfTheDayPreview(this, LocalDateTime.now(), tm);
        dayPreview.setOnAction(this::taskPressed);
        centerHBox.getChildren().setAll(dayPreview, taskPreview);
    }

    private void initializeSideBar()
    {
        SideBar sideBar = new SideBar(memoryHandler);
        mainBorderPane.setLeft(sideBar);

        sideBar.setOnAction(
                this::homePressed,
                this::newTaskButtonPressed,
                this::statisticsPressed,
                this::usersPressed,
                this::settingsPressed);
    }

    public void newTaskButtonPressed(ActionEvent event)
    {
        viewHandler.openNewTaskView();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTopBar();
        restartSceneAfterPreviousUse();
    }

    @Override
    public void restartSceneAfterPreviousUse() {
        initializeCenterScreen();
        initializeSideBar();
    }
}
