package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.model.TaskManagerApp;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import lombok.Setter;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class DirectoryViewController extends SideAndTopBarControllerBase implements ControllerInterface, Initializable {

    @Setter
    private TaskDirectory taskDirectory;
    private MessageSource messageSource;
    private TaskManagerApp taskManagerApp;

    @FXML
    private VBox mainVBox;

    @Autowired
    DirectoryViewController(ViewHandler viewHandler,
                            MemoryHandler memoryHandler,
                            MessageSource messageSource,
                            TaskManagerApp taskManagerApp)
    {
        super(viewHandler, memoryHandler);
        this.messageSource = messageSource;
        this.taskManagerApp = taskManagerApp;
    }

    @Override
    public void restartSceneAfterPreviousUse() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSideBar();
        initializeTopBar();
        mainVBox.getStyleClass().add("main-screen-background-element");
    }

    public void deleteButtonPressed(ActionEvent event)
    {}
    public void saveButtonPressed(ActionEvent event)
    {
        // @TODO update directory title
        viewHandler.back();
    }
    public void cancelButtonPressed(ActionEvent event)
    {
        viewHandler.back();
    }

    private boolean checkUserInput(String input)
    {
        return !input.isEmpty();
    }
    private void handleInvalidInputDialog()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(messageSource.getMessage("logInScreenAddUserTitle",
                null, "notFound", Locale.getDefault()));
        alert.setHeaderText(messageSource.getMessage("logInScreenAddUserHeader",
                null, "notFound", Locale.getDefault()));
        alert.setContentText(messageSource.getMessage("logInScreenAddUserContext",
                null, "notFound", Locale.getDefault()));
    }

    public void addSubDirectoryButtonPressed(ActionEvent event)
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(messageSource.getMessage("logInScreenAddUserTitle",
                null, "notFound", Locale.getDefault()));
        dialog.setHeaderText(messageSource.getMessage("logInScreenAddUserHeader",
                null, "notFound", Locale.getDefault()));
        dialog.setContentText(messageSource.getMessage("logInScreenAddUserContext",
                null, "notFound", Locale.getDefault()));

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent())
        {
            String userInput = result.get();
            if(checkUserInput(userInput))
            {

                taskManagerApp.newDirectory(taskDirectory.newSubDirectory(userInput));
            }
            else
            {
                handleInvalidInputDialog();
            }

        }
    }

}
