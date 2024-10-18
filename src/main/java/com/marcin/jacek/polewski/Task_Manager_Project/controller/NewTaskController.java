package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.CannotGoBackError;
import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.InvalidUserInputException;
import com.marcin.jacek.polewski.Task_Manager_Project.model.TaskManagerApp;
import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.AllTasksTreeView;
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
public class NewTaskController extends TaskControllerBase implements Initializable, ControllerInterface {

    private List<SubTask> listOfSubTasks = new ArrayList<>();

    @Autowired
    NewTaskController(ViewHandler viewHandler,
                      TaskManagerApp taskManagerApp,
                      MessageSource messageSource,
                      MemoryHandler memoryHandler)
    {
        super(viewHandler, taskManagerApp, messageSource, memoryHandler);
    }

    private void createNewTask()
    {
        String title = getTitleTextField().getText();

        LocalDate scheduledDate = getScheduledDatePicker().getValue();
        LocalTime scheduledTime = getScheduledTimePicker().getValue();
        LocalDateTime scheduledDateTime = LocalDateTime.of(scheduledDate, scheduledTime);

        LocalDate dueDate = getDueDatePicker().getValue();
        LocalTime dueTime = getDueTimePicker().getValue();
        LocalDateTime dueDateTime = LocalDateTime.of(dueDate, dueTime);

        String notes = getNotesTextArea().getText();

        TaskDirectory parentFolder = getSelectedTaskDirectory();

        Task newTask = new Task(0, title, scheduledDateTime, dueDateTime, parentFolder);
        newTask.setNote(notes);
        newTask.setSubTasks(listOfSubTasks);

        getTaskManagerApp().newTask(newTask);

        exitThisScene();
    }


    public void saveButtonPressed(ActionEvent event)
    {
        try{
            checkIfUserInputCorrect();
            createNewTask();
        } catch (InvalidUserInputException e)
        {
            handleInvalidUserInputAlert(e);
        }
    }

    public void initalizeSubTaskView() {

        subTasksScrollPane.setContent(new SubTasksView(listOfSubTasks));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        initalizeSubTaskView();
    }

    @Override
    public void restartSceneAfterPreviousUse() {

        setDateAndTime(LocalDateTime.now());
        getTitleTextField().setText(null);
        getNotesTextArea().setText(null);
        getTreeView().getSelectionModel().select(null);
        listOfSubTasks.clear();
    }
}
