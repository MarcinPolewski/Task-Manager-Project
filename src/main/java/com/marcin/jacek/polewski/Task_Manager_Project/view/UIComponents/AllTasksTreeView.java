package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryItem;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

import java.util.*;

public class AllTasksTreeView extends TreeView<TaskDirectoryItem> {
    private List<TaskDirectory> directories;

    private EventHandler<TaskPressedEvent> taskPressedEventHandler;
    private EventHandler<TaskDirectoryPressedEvent> directoryPressedEventHandler;

    private boolean onlyDirectories = false;

    @Getter
    private Map<TaskDirectory, TreeItem<TaskDirectoryItem>> map = new HashMap<>();

    public AllTasksTreeView(List<TaskDirectory> directories, boolean onlyDirectories)
    {
        super();
        this.onlyDirectories = onlyDirectories;
        this.directories = directories;
        loadTreeStructure();

    }


    public void addDirectoryToTree(TaskDirectory directory)
    {
        TreeItem<TaskDirectoryItem> treeItem = new TreeItem<>(directory);
        if(!onlyDirectories && directory.getTasks() != null)
        {
            for(Task task : directory.getTasks())
            {
                treeItem.getChildren().add(new TreeItem<>(task));
            }
        }


        map.put(directory, treeItem);

        boolean treeItemFound = false;
        TaskDirectory parent = directory.getParentDirectory();
        while(parent!=null && !treeItemFound)
        {
                TreeItem<TaskDirectoryItem> mapSearchResult = map.get(parent);
                if(mapSearchResult == null) // no such directory currently in tree view
                {
                    TreeItem<TaskDirectoryItem> tempTreeItem = treeItem;

                    directory = directory.getParentDirectory();
                    treeItem = new TreeItem<>();
                    treeItem.getChildren().setAll(tempTreeItem);
                }
                else
                {
                    mapSearchResult.getChildren().add(treeItem);
                    treeItemFound = true;
                }
        }
        if(parent==null && !treeItemFound)
        {
            this.getRoot().getChildren().add(treeItem);
        }
    }

    private void loadTreeStructure()
    {
        this.setRoot(new TreeItem<>());
        for(TaskDirectory dir : directories)
        {
            addDirectoryToTree(dir);
        }
        this.setShowRoot(false);
    }

    public void treeItemPressed(MouseEvent event)
    {
        TreeItem<TaskDirectoryItem> treeItem = this.getSelectionModel().getSelectedItem();
        if (treeItem != null && event.getEventType().equals(MouseEvent.MOUSE_CLICKED) && event.getClickCount() == 1)
        {
            TaskDirectoryItem directoryItem = treeItem.getValue();
            if( directoryPressedEventHandler!=null && directoryItem instanceof TaskDirectory)
            {
                directoryPressedEventHandler.handle(new TaskDirectoryPressedEvent((TaskDirectory)directoryItem));
            }
            else if ( taskPressedEventHandler!=null && directoryItem instanceof Task)
            {
                taskPressedEventHandler.handle(new TaskPressedEvent((Task)directoryItem));
            }
        }
    }

    public void setOnAction(EventHandler<TaskPressedEvent> taskPressed, EventHandler<TaskDirectoryPressedEvent> directoryPressed)
    {
        this.setOnMouseClicked(this::treeItemPressed);
        this.taskPressedEventHandler = taskPressed;
        this.directoryPressedEventHandler = directoryPressed;
    }

    public void setOnAction(EventHandler<TaskDirectoryPressedEvent> directoryPressed)
    {
        this.setOnMouseClicked(this::treeItemPressed);
        this.directoryPressedEventHandler = directoryPressed;
    }

}
