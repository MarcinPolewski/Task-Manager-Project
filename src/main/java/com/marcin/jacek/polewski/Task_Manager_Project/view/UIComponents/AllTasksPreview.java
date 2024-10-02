package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryItem;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.antlr.v4.runtime.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllTasksPreview extends VBox {
    private TreeView<TaskDirectoryItem> treeView;
    private TaskDirectoryService taskDirectoryService;

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

    public AllTasksPreview(TaskDirectoryService taskDirectoryService)
    {
        super();
        this.taskDirectoryService = taskDirectoryService;
        loadTreeStructure();
        this.getChildren().setAll(treeView);
    }

}
