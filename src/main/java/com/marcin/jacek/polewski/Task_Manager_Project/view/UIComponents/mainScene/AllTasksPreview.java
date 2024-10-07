package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents.mainScene;

import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskDirectoryPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.Events.TaskPressedEvent;
import com.marcin.jacek.polewski.Task_Manager_Project.controller.ControllerInterface;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryItem;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllTasksPreview extends VBox {
    private TreeView<TaskDirectoryItem> treeView;
    private TaskDirectoryService taskDirectoryService;
    private ControllerInterface controller;

    private EventHandler<TaskPressedEvent> taskPressed;
    private EventHandler<TaskDirectoryPressedEvent> directoryPressed;

    public void setOnAction(Object taskPressed, Object directoryPressed) {

    }

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

    public AllTasksPreview(ControllerInterface controller, TaskDirectoryService taskDirectoryService)
    {
        super();
        this.controller = controller;
        this.taskDirectoryService = taskDirectoryService;
        loadTreeStructure();
        this.getChildren().setAll(treeView);
    }


    private void loadTreeStructure()
    {
        TaskDirectory root = taskDirectoryService.getRoot();

        Queue<QueueItem> directoriesToProcess = new LinkedList <QueueItem>();

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
            if(tasks!=null)
            {
                for(Task task : tasks)
                {
                    currentTreeItem.getChildren().add(new TreeItem<>(task));
                }
            }

        }
        rootTreeItem.setExpanded(true);
        this.treeView = new TreeView<>(rootTreeItem);
        treeView.setShowRoot(false);
    }


    public void treeItemPressed(MouseEvent event)
    {
        System.out.println("pressed");
        TreeItem<TaskDirectoryItem> treeItem = treeView.getSelectionModel().getSelectedItem();
        if (treeItem != null && event.getEventType().equals(MouseEvent.MOUSE_CLICKED) && event.getClickCount() == 1)
        {
            TaskDirectoryItem directoryItem = treeItem.getValue();
            if(directoryItem instanceof TaskDirectory)
            {
                directoryPressed.handle(new TaskDirectoryPressedEvent((TaskDirectory)directoryItem));
            }
            else if (directoryItem instanceof Task)
            {
                taskPressed.handle(new TaskPressedEvent((Task)directoryItem));
            }
        }
    }

    public void setOnAction(EventHandler<TaskPressedEvent> taskPressed, EventHandler<TaskDirectoryPressedEvent> directoryPressed)
    {
        treeView.setOnMouseClicked(this::treeItemPressed);
        this.taskPressed = taskPressed;
        this.directoryPressed = directoryPressed;
    }

}
