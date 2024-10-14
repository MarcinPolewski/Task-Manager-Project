package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import javafx.fxml.Initializable;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DirectoryViewController extends SideAndTopBarControllerBase implements ControllerInterface, Initializable {

    @Setter
    private TaskDirectory taskDirectory;

    @Autowired
    DirectoryViewController(ViewHandler viewHandler, MemoryHandler memoryHandler)
    {
        super(viewHandler, memoryHandler);
    }

    @Override
    public void restartSceneAfterPreviousUse() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSideBar();
        initializeTopBar();
    }
}
