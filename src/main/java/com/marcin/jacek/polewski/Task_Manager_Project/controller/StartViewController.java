package com.marcin.jacek.polewski.Task_Manager_Project.controller;


import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ImageId;
import com.marcin.jacek.polewski.Task_Manager_Project.view.StartScreenAnimator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class StartViewController implements Initializable {
    @FXML
    private ImageView iconView;
    @FXML
    private Label greetingLabel;
    @FXML
    private Label mottoLabel;
    private MemoryHandler memoryHandler;
    private MessageSource messageSource;
    private StartScreenAnimator animator;

    @Autowired
    StartViewController(MemoryHandler memoryHandler,
                        MessageSource messageSource,
                        StartScreenAnimator animator)
    {
        this.memoryHandler = memoryHandler;
        this.messageSource = messageSource;
        this.animator = animator;
    }

    private void runAnnimations() {
        Window window = iconView.getScene().getWindow();
        try {
            animator.start(iconView, window);
            Thread.sleep(500);
            animator.start(greetingLabel, window);
            Thread.sleep(500);
            animator.start(mottoLabel, window);
        } catch (InterruptedException e) {
        }
    }


    public void windowStartedBeingShown()
    {
        runAnnimations();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");
        // adding logo
        try{
            iconView.setImage(memoryHandler.getImage(ImageId.APP_LOGO));
        } catch(IOException e)
        {
            System.out.println("failed to load image");
        }

        // setting text on labels
        greetingLabel.setText(messageSource.getMessage("startScreenPrompt", null, "notFound", Locale.getDefault()));
        mottoLabel.setText(messageSource.getMessage("startScreenMotto", null, "notFound", Locale.getDefault()));

        // adding a listener to a scene, so a method will be called when scene with this button is added to a window
        iconView.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                // Listen for when the scene's window property is set (i.e., when the scene is attached to a window)
                newScene.windowProperty().addListener((obs, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        // The scene is added to the window, now listen for when it's shown
                        newWindow.showingProperty().addListener((o, oldValue, newValue) -> {
                            if (newValue) {
                                // The window is shown
                                windowStartedBeingShown();
                            }
                        });
                    }
                });
            }
        });
        System.out.println("");

    }
}
