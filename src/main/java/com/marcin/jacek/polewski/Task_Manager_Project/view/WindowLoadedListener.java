package com.marcin.jacek.polewski.Task_Manager_Project.view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Window;

public class WindowLoadedListener {

    public static void addWindowLoadedListener(Node nodeFromScene, Runnable onWindowLoaded) {

        nodeFromScene.sceneProperty().addListener((o, oldVal, newVal) -> {
            if(newVal != null)
            {
                newVal.windowProperty().addListener((oo, oldWindow, newWindow) -> {
                    if(newWindow != null)
                    {
                        newWindow.showingProperty().addListener((ooo, oldIsShowing, newIsShowing) -> {
                            if(newIsShowing){
                                Platform.runLater(onWindowLoaded);
                            }
                        });
                    }

                });
            }
        });
    }
}