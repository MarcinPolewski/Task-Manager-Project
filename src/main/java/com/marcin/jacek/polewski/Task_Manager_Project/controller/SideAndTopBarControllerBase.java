package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.util.MemoryHandler;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.SideBar;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.topBar.TopBar;
import com.marcin.jacek.polewski.Task_Manager_Project.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SideAndTopBarControllerBase {
    @FXML
    BorderPane mainBorderPane;

    ViewHandler viewHandler;
    MemoryHandler memoryHandler;

    SideBar sideBar;

    SideAndTopBarControllerBase(ViewHandler viewHandler, MemoryHandler memoryHandler)
    {
        this.memoryHandler = memoryHandler;
        this.viewHandler = viewHandler;
    }


    public void homeButtonPressed (ActionEvent event)
    {
        viewHandler.switchToMainScene();
    }
    public void addButtonPressed (ActionEvent event)
    {
        // do noting ? reload ?
        viewHandler.openNewTaskView();
    }
    public void statisticsButtonPressed (ActionEvent event)
    {
    }
    public void usersButtonPressed (ActionEvent event)
    {}
    public void  settingsButtonPressed (ActionEvent event)
    {}

    void initializeSideBar()
    {
        SideBar sideBar = new SideBar(memoryHandler);
        mainBorderPane.setLeft(sideBar);
        sideBar.setOnAction(this::homeButtonPressed,
                this::addButtonPressed,
                this::statisticsButtonPressed,
                this::usersButtonPressed,
                this::settingsButtonPressed);
    }

    void initializeTopBar()
    {
        HBox hbox = new HBox();
        hbox.getChildren().setAll(new TopBar());
        hbox.setFillHeight(false);
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        mainBorderPane.setTop(hbox);
    }


}
