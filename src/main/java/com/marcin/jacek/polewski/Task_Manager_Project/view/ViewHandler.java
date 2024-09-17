package com.marcin.jacek.polewski.Task_Manager_Project.view;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.StartUpInitializationCompletedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/* class responsible for switching scenes*/
@Component
public class ViewHandler implements ApplicationListener<StartUpInitializationCompletedEvent> {
    private Stage mainStage;
    private SceneWrapper currentStage;
    private SceneWrapper startScene;
    private MemoryHandler memoryHandler;

    @Autowired
    ViewHandler(MemoryHandler memoryHandler)
    {
        this.memoryHandler = memoryHandler;
    }

    void setWindowIcon() throws IOException
    {
        Image icon = memoryHandler.getImage(ImageId.APP_LOGO);
        mainStage.getIcons().add(icon);
        // @TODO fix, app icon is not set
    }

    @Override
    public void onApplicationEvent(StartUpInitializationCompletedEvent event) {
        // initialization of spring has finished, now load start scene to application
        mainStage = event.getStage();
        try{
            currentStage = memoryHandler.getSceneWrapper(SceneId.START_SCENE);
            startScene = currentStage;

            mainStage.setScene(currentStage.getScene());
        } catch (IndexOutOfBoundsException e){
            System.out.println("No scene with particular id found");
        } catch (IOException e)
        {
            System.out.println("Error has occurred during loading fxml file," +
                    "according to path provided in application.properties");
        }
        try{
            setWindowIcon();
        } catch(IOException e)
        {
            System.out.println("unable to get icon");
        }
        mainStage.show();
    }
}
