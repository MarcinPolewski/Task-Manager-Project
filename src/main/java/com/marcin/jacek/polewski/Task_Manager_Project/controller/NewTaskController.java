package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.CannotGoBackError;
import com.marcin.jacek.polewski.Task_Manager_Project.model.TaskManagerApp;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.AllTasksTreeView;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

@Component
public class NewTaskController implements Initializable, ControllerInterface {

    private ViewHandler viewHandler;
    private AllTasksTreeView treeView;
    private TaskDirectory selectedTaskDirectory;
    @Setter
    private TaskManagerApp taskManagerApp;

    @FXML
    private ScrollPane directoryScrollPane;

    @FXML
    private TextField titleTextField;
    @FXML
    private DatePicker scheduledDatePicker;
    @FXML
    private ComboBox<LocalTime> scheduledTimePicker;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private ComboBox<LocalTime> dueTimePicker;
    @FXML
    private TextArea notesTextArea;

    @Autowired
    NewTaskController(ViewHandler viewHandler,
                      TaskManagerApp taskManagerApp)
    {

        this.viewHandler = viewHandler;
        this.taskManagerApp = taskManagerApp;
    }

    private void exitThisScene()
    {
        try{
            viewHandler.back();
        } catch(CannotGoBackError e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void cancelButtonPressed(ActionEvent event)
    {
        exitThisScene();
    }

    public void saveButtonPressed(ActionEvent event)
    {
        String title = titleTextField.getText();

        LocalDate scheduledDate = scheduledDatePicker.getValue();
        LocalTime scheduledTime = scheduledTimePicker.getValue();
        LocalDateTime scheduledDateTime = LocalDateTime.of(scheduledDate, scheduledTime);

        LocalDate dueDate = dueDatePicker.getValue();
        LocalTime dueTime = dueTimePicker.getValue();
        LocalDateTime dueDateTime = LocalDateTime.of(dueDate, dueTime);

        String notes = notesTextArea.getText();

        TaskDirectory parentFolder = selectedTaskDirectory;

        //Task(int taskId, String title, LocalDateTime scheduledExecution, LocalDateTime dueDate)
        Task newTask = new Task(0, title, scheduledDateTime, dueDateTime, parentFolder);
        // @TODO FIX error, because string path is not set
              /*
            adding to task directory service, becauase no path is set
         */
        newTask.setNote(notes);
        taskManagerApp.newTask(newTask);

        exitThisScene();
    }

    private void directoryPressed(TaskDirectoryPressedEvent event)
    {
        this.selectedTaskDirectory = event.getTaskDirectory();
    }


    private void initializeComboBoxes()
    {
        LocalTime time = LocalTime.of(0,0,0);

        int interval = 15;
        int numberOfIntervals = 24*(60/interval);
        for(int i=0; i!=numberOfIntervals; ++i)
        {
            scheduledTimePicker.getItems().add(time);
            dueTimePicker.getItems().add(time);
            time = time.plusMinutes(interval);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComboBoxes();
        this.treeView =  new AllTasksTreeView(taskManagerApp.getCurrentUser().getTaskManager().getTaskDirectories(), true);
        treeView.setOnAction(this::directoryPressed);
        directoryScrollPane.setContent(treeView);

    }

    @Override
    public void initializeScene() {

        titleTextField.setText(null);
        scheduledDatePicker.setValue(null);
        scheduledTimePicker.setValue(null);
        dueDatePicker.setValue(null);
        dueTimePicker.setValue(null);
        notesTextArea.setText(null);
        treeView.setSelectionModel(null);
    }
}
