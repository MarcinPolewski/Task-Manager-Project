package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.controller.ControllerInterface;
import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.InvalidTaskDate;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TasksOfTheDayPreview extends VBox {
    private HBox topBarHBox = new HBox();
    private Button previousDay= new Button("<");
    private Button nextDay = new Button(">");
    private Label dayLabel = new Label();
    private List<DayPreviewHourDisplay>hourDisplays = new ArrayList<>();
    private ControllerInterface controller;

    private LocalDateTime displayedTime;
    private TaskManager taskManager;

    private EventHandler<TaskPressedEvent> taskPressedEventHandler;

    private void clear()
    {
        for(DayPreviewHourDisplay hourDisplay: hourDisplays)
        {
            hourDisplay.clear();
        }
    }

    private void addTaskToPreview(Task task) throws InvalidTaskDate
    {
        LocalDateTime execution = task.getScheduledExecution();
        if(!execution.toLocalDate().equals(displayedTime.toLocalDate()))
            throw new InvalidTaskDate("Cannot add task to DayPreview, due to day miss match");

        int executionHour = execution.getHour();
        hourDisplays.get(executionHour).addTask(task);
    }

    private void addTasksToPreview() throws InvalidTaskDate
    {
        List<Task> tasksForTheDay = taskManager.getTasks(displayedTime.toLocalDate());
        if(tasksForTheDay != null)
        {
            for(Task task: tasksForTheDay)
            {
                addTaskToPreview(task);
            }
        }
    }

    private void updateView()
    {
        dayLabel.setText(displayedTime.toString());
        addTasksToPreview();
    }

    public void switchToPreviousDay(ActionEvent event)
    {
        displayedTime = displayedTime.minusDays(1);

        clear();
        updateView();
    }

    public void switchToNextDay(ActionEvent event)
    {
        displayedTime = displayedTime.plusDays(1);

        clear();
        updateView();
    }

    private void setUpTopBar(LocalDateTime currentTime, TaskManager taskManager)
    {
        this.displayedTime = currentTime;
        this.taskManager = taskManager;

        topBarHBox.getChildren().setAll(previousDay, dayLabel, nextDay);
        topBarHBox.setFillHeight(true);

        previousDay.setOnAction(this::switchToPreviousDay);
        nextDay.setOnAction(this::switchToNextDay);

        // add top bar to this component
        this.getChildren().add(topBarHBox);
    }

    private void setUpHourScrollView(LocalDateTime currentTime, TaskManager taskManager)
    {
        // day view setting
        VBox scrollVBox = new VBox();
        // Bind the VBox's width to the width of the ScrollPane's viewport
        scrollVBox.setFillWidth(true);


        ScrollPane scroll = new ScrollPane(scrollVBox);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //instantiate hour lines with labels assigned
        for(int hour=1; hour<= 24; ++hour)
        {
            DayPreviewHourDisplay hourDisplay = new DayPreviewHourDisplay(hour);
            hourDisplays.add(hourDisplay);
            hourDisplay.setOnAction(taskPressedEventHandler);
            scrollVBox.getChildren().add(hourDisplay);
        }

        updateView();
        this.getChildren().add(scroll);
    }

    public TasksOfTheDayPreview(ControllerInterface controller, LocalDateTime currentTime, TaskManager taskManager)
    {
        super();
        this.controller = controller;
        setUpTopBar(currentTime, taskManager);
        setUpHourScrollView( currentTime,  taskManager);
    }

    public void setOnAction(EventHandler<TaskPressedEvent> taskPressedEventHandler)
    {
        this.taskPressedEventHandler = taskPressedEventHandler;
        for(DayPreviewHourDisplay hourDisplay: hourDisplays)
        {
            hourDisplay.setOnAction(taskPressedEventHandler);
        }
    }

}
