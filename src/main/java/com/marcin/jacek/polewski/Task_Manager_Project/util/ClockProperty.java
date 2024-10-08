package com.marcin.jacek.polewski.Task_Manager_Project.util;

import com.sun.javafx.binding.StringFormatter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockProperty extends SimpleStringProperty {
    private final DateTimeFormatter formatter;


    public ClockProperty()
    {
        // "E ,d.M.y , HH:mm:ss"
        super(LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, d.M.y, HH:mm:ss")));
        this.formatter = DateTimeFormatter.ofPattern("E, d.M.y, HH:mm:ss");
        startClock();
    }

    private void startClock()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event->
        {
            set(LocalDateTime.now().format(formatter));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
