package com.marcin.jacek.polewski.Task_Manager_Project.view;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
public class ImageWrapper {
    @Setter
    private Image image;
    private Resource imageResource;
    private ImageId id;

    public ImageWrapper(ImageId id, Resource resource)
    {
        this.id = id;
        this.imageResource = resource;
    }
}
