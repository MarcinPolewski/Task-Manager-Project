package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.CannotGoBackError;
import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.InvalidUserInputException;
import com.marcin.jacek.polewski.Task_Manager_Project.model.TaskManagerApp;
import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.AllTasksTreeView;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.taskViewBase.SubTasksView;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Getter
public abstract class TaskControllerBase extends SideAndTopBarControllerBase implements Initializable, ControllerInterface{

    private AllTasksTreeView treeView;
    private TaskDirectory selectedTaskDirectory;
    List<SubTask> subTasks;
    SubTasksView subTasksView = new SubTasksView();

    private MessageSource messageSource;

    @Setter
    private TaskManagerApp taskManagerApp;
    @FXML
    private ScrollPane directoryScrollPane;
    @FXML
    ScrollPane subTasksScrollPane;
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
    @FXML
    private VBox mainVBox;
    @FXML
    private Button addSubTaskButton;
//    @FXML
//    private BorderPane mainBorderPane;

    private int interval = 15; // how many minutes between selections


    TaskControllerBase(ViewHandler viewHandler,
                       TaskManagerApp taskManagerApp,
                       MessageSource messageSource,
                       MemoryHandler memoryHandler)
    {
        super(viewHandler, memoryHandler);

        this.taskManagerApp = taskManagerApp;
        this.messageSource = messageSource;
    }

    void exitThisScene()
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

    private void runNewFolderDialog()
    {
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

            this.treeView.addDirectoryToTree(td);
            taskManagerApp.newDirectory(td);
        }


    }

    public void newFolderButtonPressed(ActionEvent event)
    {
        runNewFolderDialog();
    }


    void checkIfUserInputCorrect() throws InvalidUserInputException
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

    void handleInvalidUserInputAlert(InvalidUserInputException e)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(getMessageSource().getMessage("newTaskScreenInvalidDataTitle", null, "", Locale.getDefault()));
        errorAlert.setContentText(e.getUserPrompt());
        errorAlert.showAndWait();
    }

    private void directoryPressed(TaskDirectoryPressedEvent event)
    {
        this.selectedTaskDirectory = event.getTaskDirectory();
    }

    private void setTimePickersToNow(LocalTime time)
    {

        int defaultValueIndex = (time.getHour() * 60 + time.getMinute())/interval;
        scheduledTimePicker.setValue(scheduledTimePicker.getItems().get(defaultValueIndex));
        dueTimePicker.setValue(dueTimePicker.getItems().get(defaultValueIndex));
    }


    private void initializeComboBoxes()
    {
        LocalTime time = LocalTime.of(0,0,0);

        int numberOfIntervals = 24*(60/ interval);
        for(int i=0; i!=numberOfIntervals; ++i)
        {
            scheduledTimePicker.getItems().add(time);
            dueTimePicker.getItems().add(time);
            time = time.plusMinutes(interval);
        }

        setTimePickersToNow(LocalTime.now());
    }

    private void setDateInDatePickers(LocalDate date)
    {
        scheduledDatePicker.setValue(date);
        dueDatePicker.setValue(date);
    }


    private void initializeDatePickers()
    {
        setDateInDatePickers(LocalDate.now());
    }

    void setParametersOfTask(Task task)
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

        if(task.getCreationData()==null)
            task.setCreationData(LocalDateTime.now());

        for(SubTask st: subTasks)
        {
            st.setMainTask(task);
        }

        task.setSubTasks(subTasks);
    }

    public void addSubTaskButtonPressed(ActionEvent event)
    {
        // open pop up
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(messageSource.getMessage("logInScreenAddUserTitle",
                null, "notFound", Locale.getDefault()));
        dialog.setHeaderText(messageSource.getMessage("logInScreenAddUserHeader",
                null, "notFound", Locale.getDefault()));
        dialog.setContentText(messageSource.getMessage("logInScreenAddUserContext",
                null, "notFound", Locale.getDefault()));


        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // add to sub tasks
            SubTask createdTask = new SubTask(result.get());
            subTasks.add(createdTask);

            // create a button
            subTasksView.newSubTask(createdTask);
        }



    }

    public void initalizeSubTaskView() {
        subTasksView = new SubTasksView();
        subTasksScrollPane.setContent(subTasksView);
    }


    private void initializeTexts()
    {
        sceneTitle.setText(messageSource.getMessage("newTaskScreenHeadLabel", null, "", Locale.getDefault()));
        addSubtaskButton.setText(messageSource.getMessage("newTaskScreenAddSubTaskButton", null, "", Locale.getDefault()));
        cancelButton.setText(messageSource.getMessage("newTaskScreenCancelButton", null, "", Locale.getDefault()));
        saveTaskButton.setText(getMessageSource().getMessage("newTaskScreenSaveTaskButton", null, "", Locale.getDefault()));


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
        initializeTopBar();
        initializeSideBar();
        initalizeSubTaskView();

        this.treeView =  new AllTasksTreeView(taskManagerApp.getCurrentUser().getTaskManager().getTaskDirectories(), true);
        treeView.setOnAction(this::directoryPressed);
        directoryScrollPane.setContent(treeView);
        mainVBox.getStyleClass().add("main-screen-background-element");

    }

    public void setDateAndTime(LocalDateTime dt)
    {
        setDateInDatePickers(dt.toLocalDate());
        setTimePickersToNow(dt.toLocalTime());
    }


    public abstract void restartSceneAfterPreviousUse();
    public abstract void saveButtonPressed(ActionEvent event);

}
