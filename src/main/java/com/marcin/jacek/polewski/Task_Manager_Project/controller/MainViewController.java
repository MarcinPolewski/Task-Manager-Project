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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class MainViewController extends SideAndTopBarControllerBase implements Initializable, ControllerInterface {
    @FXML
    private HBox centerHBox;

    private TaskManagerApp taskManagerApp;
    private MessageSource messageSource;

    private AllTasksPreview taskPreview;
    private TasksOfTheDayPreview dayPreview;


    @Autowired
    MainViewController(TaskManagerApp taskManagerApp,
                       ViewHandler viewHandler,
                       MemoryHandler memoryHandler,
                       MessageSource messageSource)
    {
        super(viewHandler, memoryHandler);
        this.taskManagerApp = taskManagerApp;
        this.viewHandler = viewHandler;
        this.memoryHandler = memoryHandler;
        this.messageSource = messageSource;
    }


    private void taskPressed(TaskPressedEvent taskPressedEvent)
    {
        viewHandler.openTaskView(taskPressedEvent.getTask());
    }

    private void directoryPressed(TaskDirectoryPressedEvent directoryPressedEvent)
    {
        viewHandler.openDirectoryView(directoryPressedEvent.getTaskDirectory());
    }

    public void newFolderPressed(ActionEvent event)
    {
        // handler dialog here
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(messageSource.getMessage("logInScreenAddUserTitle",
                null, "notFound", Locale.getDefault()));
        dialog.setHeaderText(messageSource.getMessage("logInScreenAddUserHeader",
                null, "notFound", Locale.getDefault()));
        dialog.setContentText(messageSource.getMessage("logInScreenAddUserContext",
                null, "notFound", Locale.getDefault()));


        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            TaskManager tm = taskManagerApp.getCurrentUser().getTaskManager();
            TaskDirectory td = new TaskDirectory(result.get(), tm);
            tm.getTaskDirectories().add(td);

            this.taskPreview.addFolder(td);
            taskManagerApp.newDirectory(td);
        }
    }


    private void initializeCenterScreen()
    {
        List<TaskDirectory> directories = taskManagerApp.getCurrentUser().getTaskManager().getTaskDirectories();
        taskPreview = new AllTasksPreview(directories);

        taskPreview.setOnAction(this::taskPressed, this::directoryPressed, this::newFolderPressed);

        TaskManager tm = taskManagerApp.getCurrentUser().getTaskManager();

        dayPreview = new TasksOfTheDayPreview(this, LocalDateTime.now(), tm);
        dayPreview.setOnAction(this::taskPressed);
        centerHBox.getChildren().setAll(dayPreview, taskPreview);
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
