package com.marcin.jacek.polewski.Task_Manager_Project.view;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.StartUpInitializationCompletedEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/* class responsible for switching scenes*/
@Component
public class ViewHandler implements ApplicationListener<StartUpInitializationCompletedEvent> {
    private Stage mainStage;
    private final Resource startFxml;

    ViewHandler(
            @Value("classpath:/view/start-view.fxml") Resource resource
    )
    {
        startFxml = resource;
    }

    @Override
    public void onApplicationEvent(StartUpInitializationCompletedEvent event) {
        // initialization of spring has finished, now load start scene to application
        try{
            mainStage = event.getStage();
            URL url = startFxml.getURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 500, 500);
            mainStage.setScene(scene);
            mainStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
