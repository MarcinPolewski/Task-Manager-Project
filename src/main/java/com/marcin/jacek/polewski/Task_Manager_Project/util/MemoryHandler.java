package com.marcin.jacek.polewski.Task_Manager_Project.util;

import com.marcin.jacek.polewski.Task_Manager_Project.view.SceneId;
import com.marcin.jacek.polewski.Task_Manager_Project.view.SceneWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;


@Component
public class MemoryHandler {

    private final Map<SceneId, SceneWrapper> scenes = new HashMap<>(); // maps scene id with object containing everything about this scene

    public MemoryHandler(
            @Value("${scene.startScene.name}") String startSceneName,
            @Value("classpath:${scene.startScene.path}") Resource startSceneResource,
            @Value("classpath:${scene.startScene.css.path}") Resource startSceneCSSResource,
            @Value("classpath:${scene.default.css.path}") Resource defaultCSSResource
    )
    {

        // adding scenes to scenes map @set
        scenes.put(SceneId.START_SCENE, new SceneWrapper(SceneId.START_SCENE, startSceneName, startSceneResource, startSceneCSSResource));
        // @TODO add all other scenes

        SceneWrapper.setDefaultCSSResource(defaultCSSResource);
    }

    public SceneWrapper getSceneWrapper(SceneId sceneId) throws IndexOutOfBoundsException, IOException
    {
        SceneWrapper wrapper =  scenes.get(sceneId);
        if(wrapper == null)
            throw new IndexOutOfBoundsException("scene map does not have mapping for sceneId: " + sceneId);

        // if class has not been loaded, load
        if(!wrapper.isFXMLLoaded())
        {
            // load fxml file
            URL url = wrapper.getFxmlResource().getURL(); // thows IOExc
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();        // throws IOException
            Scene scene = new Scene(root);

            wrapper.setController(loader.getController());
            wrapper.setScene(scene);

            // load css file
            String defaultCss = Files.readString(SceneWrapper.getDefaultCSSResource().getFile().toPath());
            String scenesCss = Files.readString(wrapper.getSceneCSSResource().getFile().toPath());

            scene.getStylesheets().add(defaultCss);
            scene.getStylesheets().add(scenesCss);
        }
        return wrapper;
    }
}

