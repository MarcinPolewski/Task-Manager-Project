package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.CannotGoBackError;
import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.InvalidUserInputException;
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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class NewTaskController implements Initializable, ControllerInterface {

    private ViewHandler viewHandler;
    private AllTasksTreeView treeView;
    private TaskDirectory selectedTaskDirectory;

    private MessageSource messageSource;

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
    @FXML
    private Label sceneTitle;
    @FXML
    private Button addSubtaskButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveTaskButton;
    @FXML
    private Label taskTitleLabel;
    @FXML
    private Label scheduledExecutionLabel;
    @FXML
    private Label dueDateLabel;
    @FXML
    private Label notesLabel;
    @FXML
    private Label folderLabel;



            @Autowired
    NewTaskController(ViewHandler viewHandler,
                      TaskManagerApp taskManagerApp,
                      MessageSource messageSource)
    {

        this.viewHandler = viewHandler;
        this.taskManagerApp = taskManagerApp;
        this.messageSource = messageSource;
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

    private void checkIfUserInputCorrect() throws InvalidUserInputException
    {

        String promptText;
        if(titleTextField.getText().isEmpty())
        {
            promptText = (messageSource.getMessage("newTaskScreenInvalidTitle", null, "", Locale.getDefault()));
            throw new InvalidUserInputException("title cannot be empty", promptText);
        }

        if(scheduledDatePicker.getValue() == null || scheduledTimePicker.getValue() == null)
        {
            promptText = (messageSource.getMessage("newTaskScreenInvalidScheduledDate", null, "", Locale.getDefault()));
            throw new InvalidUserInputException("scheduled date or time cannot be empty", promptText);
        }
        if(dueDatePicker.getValue() == null || dueDatePicker.getValue() == null)
        {
            promptText = (messageSource.getMessage("newTaskScreenInvalidDueDate", null, "", Locale.getDefault()));
            throw new InvalidUserInputException("due date or time cannot be empty", promptText);
        }
        if(selectedTaskDirectory == null)
        {
            promptText = (messageSource.getMessage("newTaskScreenInvalidFolder", null, "", Locale.getDefault()));
            throw new InvalidUserInputException("no folder selected", promptText);
        }

    }

    private void createNewTask()
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
        newTask.setNote(notes);

        taskManagerApp.newTask(newTask);

        exitThisScene();
    }


    public void saveButtonPressed(ActionEvent event)
    {
        try{
            checkIfUserInputCorrect();
            createNewTask();
        } catch (InvalidUserInputException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle(messageSource.getMessage("newTaskScreenInvalidDataTitle", null, "", Locale.getDefault()));
            errorAlert.setContentText(e.getUserPrompt());
            errorAlert.showAndWait();

        }
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

        int defaultValueIndex = (LocalTime.now().getHour() * 60 + LocalTime.now().getMinute())/interval;
        scheduledTimePicker.setValue(scheduledTimePicker.getItems().get(defaultValueIndex));
        dueTimePicker.setValue(dueTimePicker.getItems().get(defaultValueIndex));

    }

    private void initializeDatePickers()
    {
        scheduledDatePicker.setValue(LocalDate.now());
        dueDatePicker.setValue(LocalDate.now());
    }

    private void initializeTexts()
    {
        sceneTitle.setText(messageSource.getMessage("newTaskScreenHeadLabel", null, "", Locale.getDefault()));
        addSubtaskButton.setText(messageSource.getMessage("newTaskScreenAddSubTaskButton", null, "", Locale.getDefault()));
        cancelButton.setText(messageSource.getMessage("newTaskScreenCancelButton", null, "", Locale.getDefault()));
        saveTaskButton.setText(messageSource.getMessage("newTaskScreenSaveTaskButton", null, "", Locale.getDefault()));

        taskTitleLabel.setText(messageSource.getMessage("newTaskScreenTitleLabel", null, "", Locale.getDefault()));
        scheduledExecutionLabel.setText(messageSource.getMessage("newTaskScreenScheduledExecutionLabel", null, "", Locale.getDefault()));
        dueDateLabel.setText(messageSource.getMessage("newTaskScreenDueDateLabel", null, "", Locale.getDefault()));
        notesLabel.setText(messageSource.getMessage("newTaskScreenNotesLabel", null, "", Locale.getDefault()));
        folderLabel.setText(messageSource.getMessage("newTaskScreenFolderLabel", null, "", Locale.getDefault()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComboBoxes();
        initializeDatePickers();
        initializeTexts();
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
