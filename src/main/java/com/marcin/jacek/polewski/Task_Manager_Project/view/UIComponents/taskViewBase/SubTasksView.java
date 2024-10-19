package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.taskViewBase;

import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SubTasksView extends VBox {
    private List<SubTask> subTasks;
    private List<SubTaskPanel> panels = new ArrayList<>();

    public SubTasksView(){
        super();
    }
    public SubTasksView(List<SubTask> subTasks)
    {
        super();
        setSubTasks(subTasks);
    }

    public void newSubTask(SubTask st)
    {
        SubTaskPanel panel = new SubTaskPanel(st);
        panel.setOnTaskDelete(this::subTaskDeletePressed);
        panels.add(panel);
        this.getChildren().add(panel);
    }

    public void clear()
    {
        this.getChildren().clear();
    }
    public void setSubTasks(List<SubTask> subTasks)
    {
        this.subTasks = subTasks;
        clear();
        for(SubTask st : subTasks)
        {
            newSubTask(st);
        }
    }

    public void subTaskDeletePressed(ActionEvent event)
    {
        // remove from list

        // taskmanager app.delete ( remove from task and db )

    }
}
