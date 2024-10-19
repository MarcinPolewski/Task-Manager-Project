package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.InvalidUserInputException;
import com.marcin.jacek.polewski.Task_Manager_Project.model.TaskManagerApp;
import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryItem;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.taskViewBase.SubTasksView;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class TaskViewController extends TaskControllerBase implements ControllerInterface, Initializable {

    @FXML
    private Button deleteButton;

    private Task task;


    @Autowired
    TaskViewController(ViewHandler viewHandler,
                       TaskManagerApp taskManagerApp,
                       MessageSource messageSource,
                       MemoryHandler memoryHandler)
    {
        super(viewHandler, taskManagerApp, messageSource, memoryHandler);
    }


    private void updateTask()
    {
        setParametersOfTask(task);

        getTaskManagerApp().updateTask(task);
        exitThisScene();
    }

    @Override
    public void saveButtonPressed(ActionEvent event) {
        try{
            checkIfUserInputCorrect();
            updateTask();
        } catch (InvalidUserInputException e)
        {
            handleInvalidUserInputAlert(e);
        }
    }


    public void deleteButtonPressed(ActionEvent event)
    {
        System.out.println("delete pressed");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    @Override
    public void restartSceneAfterPreviousUse() {

        getTitleTextField().setText(task.getTitle());
        getScheduledDatePicker().setValue(task.getScheduledExecution().toLocalDate());
        getScheduledTimePicker().setValue(task.getScheduledExecution().toLocalTime());
        getDueDatePicker().setValue(task.getDueDate().toLocalDate());
        getDueTimePicker().setValue(task.getDueDate().toLocalTime());
        getNotesTextArea().setText(task.getNote());
        subTasksView.setSubTasks(subTasks);
//        subTasksScrollPane.setContent(new SubTasksView(subTasks));

        TreeItem<TaskDirectoryItem> treeItem = getTreeView().getMap().get(task.getEnclosingFolder());
        getTreeView().getSelectionModel().select(treeItem);
//        subTasks.clear();
    }

    public void setTask(Task task)
    {
        this.task = task;
        subTasks = new ArrayList<>(task.getSubTasks());
        restartSceneAfterPreviousUse();
    }

}
