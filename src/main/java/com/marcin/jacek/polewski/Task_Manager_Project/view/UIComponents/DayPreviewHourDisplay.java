package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class DayPreviewHourDisplay extends VBox {
    private Line line = new Line();
    private Label hourLabel = new Label();
    private HBox labelAndTasksHBox = new HBox();


    DayPreviewHourDisplay(int hour)
    {
        super();

        this.setFillWidth(true);

        hourLabel.setText(String.valueOf(hour));
        labelAndTasksHBox.getChildren().add(hourLabel);


        // padding must be greater than one pixel, therefore it below binding
        // does not cause container t o indefinitely stretch right
        double rightPadding = this.getPadding().getRight();
        if(rightPadding < 1)
        {
            rightPadding = 1;
        }

        line.endXProperty().bind(this.widthProperty()
                .subtract(rightPadding));


        this.getChildren().setAll(labelAndTasksHBox, line);
    }



    public void addTask(Task task)
    {
    }

}
