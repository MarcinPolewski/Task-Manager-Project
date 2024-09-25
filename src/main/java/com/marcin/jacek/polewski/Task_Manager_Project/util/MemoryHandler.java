package com.marcin.jacek.polewski.Task_Manager_Project.util;

import com.marcin.jacek.polewski.Task_Manager_Project.view.image.ImageId;
import com.marcin.jacek.polewski.Task_Manager_Project.view.image.ImageWrapper;
import com.marcin.jacek.polewski.Task_Manager_Project.view.scene.SceneId;
import com.marcin.jacek.polewski.Task_Manager_Project.view.scene.SceneWrapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;


@Component
public class MemoryHandler {

    private final Map<SceneId, SceneWrapper> scenes = new HashMap<>(); // maps scene id with object containing everything about this scene
    private final Map<ImageId, ImageWrapper> images = new HashMap<>();
    private final ConfigurableApplicationContext context;

    @Autowired
    public MemoryHandler(

            @Value("classpath:${images.app_logo.path}") Resource mainIconResource,
            @Value("classpath:${images.userIcon.path}") Resource userIconResource,

            @Value("${scene.startScene.name}") String startSceneName,
            @Value("classpath:${scene.startScene.path}") Resource startSceneResource,
            @Value("classpath:${scene.startScene.css.path}") Resource startSceneCSSResource,

            @Value("${scene.logInScene.name}") String logInSceneName,
            @Value("classpath:${scene.logInScene.path}") Resource logInSceneResource,
            @Value("classpath:${scene.logInScene.css.path}") Resource logInSceneCSSResource,


            @Value("classpath:${scene.default.css.path}") Resource defaultCSSResource,

            ConfigurableApplicationContext context
    )
    {

        this.context = context;
        // adding scenes to scenes map @TODO add all other scenes
        scenes.put(SceneId.START_SCENE, new SceneWrapper(SceneId.START_SCENE, startSceneName, startSceneResource, startSceneCSSResource));
        scenes.put(SceneId.LOG_IN_SCENE, new SceneWrapper(SceneId.LOG_IN_SCENE, logInSceneName, logInSceneResource, logInSceneCSSResource));


        // adding craeting images wrappers
        images.put(ImageId.APP_LOGO, new ImageWrapper(ImageId.APP_LOGO, mainIconResource));
        images.put(ImageId.USER_ICON, new ImageWrapper(ImageId.USER_ICON, userIconResource));

        SceneWrapper.setDefaultCSSResource(defaultCSSResource);
    }

    public Image getImage(ImageId imageId) throws IOException
    {
        ImageWrapper wrapper = images.get(imageId);
        if(wrapper == null)
            throw new IndexOutOfBoundsException("images does not have a mapping for this imageId:" + imageId);

        if (wrapper.getImage() == null)
        {
            Resource resource = wrapper.getImageResource();
            InputStream inputStream = resource.getInputStream();
            wrapper.setImage(new Image(inputStream));
        }

        return wrapper.getImage();
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
            URL url = wrapper.getFxmlResource().getURL(); // throws IOExc
            FXMLLoader loader = new FXMLLoader(url);

            // Set the controller factory to retrieve the controller from Spring
            // thanks to that dependency injection to FX controllers is now possible
            loader.setControllerFactory(context::getBean);

            Parent root = loader.load();        // throws IOException
            Scene scene = new Scene(root);

            wrapper.setController(loader.getController());
            wrapper.setScene(scene);

            // load css file
            String defaultCss = SceneWrapper.getDefaultCSSResource().getURL().toExternalForm();
            String scenesCss = wrapper.getSceneCSSResource().getURL().toExternalForm();
            // @TODO add caching defaultCSS

            scene.getStylesheets().add(defaultCss);
            scene.getStylesheets().add(scenesCss);
        }

        return wrapper;
    }
}