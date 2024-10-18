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
    private List<SubTask> subTasks;

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
        String title = getTitleTextField().getText();
        task.setTitle(title);

        LocalDate scheduledDate = getScheduledDatePicker().getValue();
        LocalTime scheduledTime = getScheduledTimePicker().getValue();
        LocalDateTime scheduledDateTime = LocalDateTime.of(scheduledDate, scheduledTime);
        task.setScheduledExecution(scheduledDateTime);

        LocalDate dueDate = getDueDatePicker().getValue();
        LocalTime dueTime = getDueTimePicker().getValue();
        LocalDateTime dueDateTime = LocalDateTime.of(dueDate, dueTime);
        task.setDueDate(dueDateTime);

        String notes = getNotesTextArea().getText();
        task.setNote(notes);

        TaskDirectory parentFolder = getSelectedTaskDirectory();
        task.setEnclosingFolder(parentFolder);

        task.setSubTasks(subTasks);

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
        subTasksScrollPane.setContent(new SubTasksView(subTasks));

        TreeItem<TaskDirectoryItem> treeItem = getTreeView().getMap().get(task.getEnclosingFolder());
        getTreeView().getSelectionModel().select(treeItem);
    }

    public void setTask(Task task)
    {
        this.task = task;
        subTasks = new ArrayList<>(task.getSubTasks());
        restartSceneAfterPreviousUse();
    }

}
