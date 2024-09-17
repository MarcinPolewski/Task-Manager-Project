package com.marcin.jacek.polewski.Task_Manager_Project.Events;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * StartUpInitializationCompletedEvent is a custom Spring event that is used to notify when the JavaFX primary stage is ready.
 * It extends ApplicationEvent, which is part of the Spring event framework.
 */
public class StartUpInitializationCompletedEvent extends ApplicationEvent {
    /**
     * Gets the JavaFX Stage object from the event source.
     * @return the primary JavaFX Stage.
     */
    public Stage getStage() {
        return Stage.class.cast(getSource());
    }

    /**
     * Constructor for the StageReadyEvent, taking the JavaFX Stage as the event source.
     * @param source The primary JavaFX Stage.
     */
    public StartUpInitializationCompletedEvent(Stage source) {
        super(source); // Call the parent constructor to initialize the event
    }
}
