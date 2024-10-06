package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DayPreviewTaskButton extends Button {
    DayPreviewTaskButton(Task task)
    {
        VBox vbox = new VBox();
        Label taskName = new Label(task.getTitle());
        Label creation = new Label("creation: " + task.getCreationData());
        Label due = new Label("due: " + task.getDueDate());

        vbox.getChildren().setAll(taskName, creation, due);
        this.setGraphic(vbox);
    }

}
