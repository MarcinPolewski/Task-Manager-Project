package com.marcin.jacek.polewski.Task_Manager_Project.view.UIComponents;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.antlr.v4.runtime.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllTasksPreview extends VBox {
    private TreeView<TaskDirectory> treeView;
    private TaskDirectoryService taskDirectoryService;

    private class QueueItem
    {
        @Getter
        private TaskDirectory taskDirectory;
        @Getter
        private TreeItem<TaskDirectory> treeItem;

        QueueItem(TaskDirectory taskDir, TreeItem<TaskDirectory> treeItem)
        {
            this.taskDirectory = taskDir;
            this.treeItem = treeItem;
        }

    }

    private void loadTreeStructure()
    {
        TaskDirectory root = taskDirectoryService.getRoot();

        Queue<QueueItem> directoriesToProcess = new LinkedList <QueueItem>();
        TreeItem<TaskDirectory> rootTreeItem = new TreeItem<>();
        directoriesToProcess.add(new QueueItem(root, rootTreeItem));


        while(!directoriesToProcess.isEmpty())
        {
            QueueItem currentFolder = directoriesToProcess.poll();

            List<TaskDirectory> subFolders = currentFolder.getTaskDirectory().getSubDirectories();

            TreeItem<TaskDirectory> currentTreeItem = currentFolder.getTreeItem();
            if(subFolders!=null)
            {
                for(TaskDirectory subFolder: subFolders)
                {
                    TreeItem<TaskDirectory> subTreeItem = new TreeItem<>(subFolder);
                    currentTreeItem.getChildren().add(subTreeItem);
                    directoriesToProcess.add(new QueueItem(subFolder, subTreeItem));
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
