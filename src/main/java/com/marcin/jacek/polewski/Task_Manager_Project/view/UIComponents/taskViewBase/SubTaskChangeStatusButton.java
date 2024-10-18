package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.taskViewBase;

import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.TaskState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;

public class SubTaskChangeStatusButton<T> extends Button {

    private SubTask subTask;
    private List<T> possibleStates;
    private int stateIdx;

    private EventHandler<ActionEvent> buttonPressedHandler;

    SubTaskChangeStatusButton(SubTask subTask, List<T> possibleStates, int startIdx) {
        if(possibleStates == null || possibleStates.isEmpty())
            throw new IllegalArgumentException("list of states cannot be empty");

        this.subTask = subTask;
        this.possibleStates = possibleStates;
        this.stateIdx = startIdx;

        updateText();

        this.setOnAction(this::buttonPressed);
    }

    private void updateText()
    {
        this.setText(possibleStates.get(stateIdx).toString());
    }

    private void switchState()
    {
        ++stateIdx;
        if(stateIdx>=possibleStates.size())
            stateIdx = 0;
        updateText();
    }

    private void buttonPressed(ActionEvent event)
    {
        switchState();
        buttonPressedHandler.handle(event);
    }

    public void setOnPressed(EventHandler<ActionEvent> buttonPressedHandler)
    {
        this.buttonPressedHandler = buttonPressedHandler;
    }

    public T getValue()
    {
        return possibleStates.get(stateIdx);
    }

}