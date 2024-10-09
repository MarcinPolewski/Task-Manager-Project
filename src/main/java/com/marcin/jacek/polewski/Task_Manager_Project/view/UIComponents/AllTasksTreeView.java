package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryItem;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene.AllTasksPreview;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllTasksTreeView extends TreeView<TaskDirectoryItem> {
    private TaskDirectoryService taskDirectoryService;

    private EventHandler<TaskPressedEvent> taskPressedEventHandler;
    private EventHandler<TaskDirectoryPressedEvent> directoryPressedEventHandler;

    private boolean onlyDirectories = false;

    @Getter
    private class QueueItem
    {
        private TaskDirectory taskDirectory;
        private TreeItem<TaskDirectoryItem> treeItem;

        QueueItem(TaskDirectory taskDir, TreeItem<TaskDirectoryItem> treeItem)
        {
            this.taskDirectory = taskDir;
            this.treeItem = treeItem;
        }

    }

    public AllTasksTreeView(TaskDirectoryService taskDirectoryService, boolean onlyDirectories)
    {
        super();
        this.onlyDirectories = onlyDirectories;
        this.taskDirectoryService = taskDirectoryService;
        loadTreeStructure();

    }



    private void loadTreeStructure()
    {
        TaskDirectory root = taskDirectoryService.getRoot();

        Queue<QueueItem> directoriesToProcess = new LinkedList<QueueItem>();

        TreeItem<TaskDirectoryItem> rootTreeItem = new TreeItem<>();
        directoriesToProcess.add(new QueueItem(root, rootTreeItem));


        while(!directoriesToProcess.isEmpty())
        {
            QueueItem currentFolder = directoriesToProcess.poll();

            List<TaskDirectory> subFolders = currentFolder.getTaskDirectory().getSubDirectories();

            TreeItem<TaskDirectoryItem> currentTreeItem = currentFolder.getTreeItem();
            if(subFolders!=null)
            {
                for(TaskDirectory subFolder: subFolders)
                {
                    TreeItem<TaskDirectoryItem> subTreeItem = new TreeItem<>(subFolder);
                    currentTreeItem.getChildren().add(subTreeItem);
                    directoriesToProcess.add(new QueueItem(subFolder, subTreeItem));
                }
            }

            List<Task> tasks =  currentFolder.getTaskDirectory().getTasks();
            if(!onlyDirectories && tasks!=null)
            {
                for(Task task : tasks)
                {
                    currentTreeItem.getChildren().add(new TreeItem<>(task));
                }
            }

        }
        rootTreeItem.setExpanded(true);
        // this.treeView = new TreeView<>(rootTreeItem);
        this.setRoot(rootTreeItem);

        //treeView.setShowRoot(false);
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
