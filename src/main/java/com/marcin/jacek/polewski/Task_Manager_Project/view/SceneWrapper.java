package com.marcin.jacek.polewski.Task_Manager_Project.view;

import javafx.scene.Scene;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
public class SceneWrapper {


    private final SceneId id;
    private final String name;
    private final Resource resource;
    @Setter private Scene scene;
    @Setter private Object controller;


    public SceneWrapper(SceneId id, String name, Resource resource)
    {
        this.id = id;
        this.name = name;
        this.resource = resource;
    }

    public boolean isFXMLLoaded()
    {
        return (scene!=null) && (controller!=null);
    }
}
