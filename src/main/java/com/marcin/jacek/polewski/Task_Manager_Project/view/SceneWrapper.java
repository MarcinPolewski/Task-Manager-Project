package com.marcin.jacek.polewski.Task_Manager_Project.view;

import javafx.scene.Scene;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
public class SceneWrapper {


    @Getter(AccessLevel.NONE)
    private static Resource defaultCSSResource;
    private final SceneId id;
    private final String name;
    private final Resource fxmlResource;
    private final Resource sceneCSSResource;
    @Setter private Scene scene;
    @Setter private Object controller;


    public SceneWrapper(SceneId id, String name, Resource fxmlResource, Resource cssResource)
    {
        this.id = id;
        this.name = name;
        this.fxmlResource = fxmlResource;
        this.sceneCSSResource = cssResource;
    }

    public boolean isFXMLLoaded()
    {
        return (scene!=null) && (controller!=null);
    }

    public static Resource getDefaultCSSResource()
    {
        return defaultCSSResource;
    }

    public static void setDefaultCSSResource(Resource res)
    {
        SceneWrapper.defaultCSSResource = res;
    }
}
