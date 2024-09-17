package com.marcin.jacek.polewski.Task_Manager_Project.util;

import com.marcin.jacek.polewski.Task_Manager_Project.view.SceneId;
import com.marcin.jacek.polewski.Task_Manager_Project.view.SceneWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;


@Component
public class MemoryHandler {

    private final Map<SceneId, SceneWrapper> scenes = new HashMap<>(); // maps scene id with object containing everything about this scene

    public MemoryHandler(
            @Value("${scene.startScene.name}") String startSceneName,
            @Value("classpath:${scene.startScene.path}") Resource startSceneResource
    )
    {

        // adding scenes to scenes map
        scenes.put(SceneId.START_SCENE, new SceneWrapper(SceneId.START_SCENE, startSceneName, startSceneResource));
        // @TODO add all other scenes
    }

    public SceneWrapper getSceneWrapper(SceneId sceneId) throws IndexOutOfBoundsException, IOException
    {
        SceneWrapper wrapper =  scenes.get(sceneId);
        if(wrapper == null)
            throw new IndexOutOfBoundsException("scene map does not have mapping for sceneId: " + sceneId);
        if(!wrapper.isFXMLLoaded())
        {
            URL url = wrapper.getResource().getURL(); // thows IOExc
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();        // throws IOException
            Scene scene = new Scene(root);

            wrapper.setController(loader.getController());
            wrapper.setScene(scene);
        }
        return wrapper;
    }
}

