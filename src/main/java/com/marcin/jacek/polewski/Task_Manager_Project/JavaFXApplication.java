package com.marcin.jacek.polewski.Task_Manager_Project;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.StartUpInitializationCompletedEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * JavaFxApplication extends the JavaFX Application class and manages the
 * initialization of both Spring and JavaFX contexts.
 */
@Component
public class JavaFXApplication extends Application {

    // The Spring context that will be used to manage beans and application lifecycle
    private ConfigurableApplicationContext context;
    /**
     * The init() method is called before the JavaFX application starts.
     * It initializes the Spring context and registers JavaFX-specific beans.
     */
    @Override
    public void init() throws Exception {
        // initialize spring context
        this.context = new SpringApplicationBuilder(Main.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    /**
     * The start() method is the entry point for the JavaFX application once the Spring context is ready.
     * This method publishes an event to notify that the primary stage is ready for use.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Publish the StageReadyEvent to signal that the primary stage is ready
        System.out.println("Java FX app started");
        this.context.publishEvent(new StartUpInitializationCompletedEvent(primaryStage));
    }

    /**
     * The stop() method is called when the JavaFX application is shutting down.
     * It closes the Spring context and ensures that the JavaFX platform exits cleanly.
     */
    @Override
    public void stop() throws Exception {
        // Close the Spring application context to clean up resources
        this.context.close();
        // Exit the JavaFX platform
        Platform.exit();
    }
}