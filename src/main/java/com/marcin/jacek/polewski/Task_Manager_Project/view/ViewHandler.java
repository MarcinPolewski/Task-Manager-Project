package com.marcin.jacek.polewski.Task_Manager_Project.view;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.StartUpInitializationCompletedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.image.ImageId;
import com.marcin.jacek.polewski.Task_Manager_Project.view.scene.SceneId;
import com.marcin.jacek.polewski.Task_Manager_Project.view.scene.SceneWrapper;
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
    private SceneWrapper currentScene;


    private MemoryHandler memoryHandler;

    @Autowired
    ViewHandler(MemoryHandler memoryHandler)
    {
        this.memoryHandler = memoryHandler;
    }

    private void setWindowIcon() throws IOException
    {
        Image icon = memoryHandler.getImage(ImageId.APP_LOGO);
        mainStage.getIcons().add(icon);

        // @TODO fix, app icon is not set
    }

    @Override
    public void onApplicationEvent(StartUpInitializationCompletedEvent event) {
        // initialization of spring has finished, now load start scene to application
        // and configure the main window
        mainStage = event.getStage();
        mainStage.setResizable(false);
        try{
            currentScene = memoryHandler.getSceneWrapper(SceneId.START_SCENE);

            mainStage.setScene(currentScene.getScene());
        } catch (IndexOutOfBoundsException e){
            System.out.println("No scene with particular id found");
        } catch (IOException e)
        {
            System.out.println("Error has occurred during loading fxml file," +
                    "according to path provided in application.properties");
        }

        try{
            setWindowIcon();
            System.out.println("Icon is set");
        } catch(IOException e)
        {
            System.out.println("unable to get icon");
        }
        mainStage.show();

    }

    public void switchToLogInScene()
    {
        if(!currentScene.getId().equals(SceneId.LOG_IN_SCENE))
        {
            try{
                currentScene = memoryHandler.getSceneWrapper(SceneId.LOG_IN_SCENE);
                mainStage.setScene(currentScene.getScene());
            } catch (IOException e)
            {
                System.out.println("Error has occured during loading scene from file" + e.getMessage());
            }
        }
        mainStage.setResizable(true);
    }

    public void switchToMainScene()
    {
        if(!currentScene.getId().equals(SceneId.MAIN_SCENE))
        {
            try{
                currentScene = memoryHandler.getSceneWrapper(SceneId.MAIN_SCENE);
                mainStage.setScene(currentScene.getScene());
            } catch (IOException e)
            {
                System.out.println("Error has occured during loading scene from file" + e.getMessage());
            }
        }
    }

    public void switchToNewTaskView()
    {
        if(!currentScene.getId().equals(SceneId.NEW_TASK_SCENE))
        {
            try{
                currentScene = memoryHandler.getSceneWrapper(SceneId.NEW_TASK_SCENE);
                mainStage.setScene(currentScene.getScene());
            } catch (IOException e)
            {
                System.out.println("Error has occured during loading scene from file" + e.getMessage());
            }
        }
    }


}
