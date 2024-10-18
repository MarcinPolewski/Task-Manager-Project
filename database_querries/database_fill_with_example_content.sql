USE `task_manager`;

-- Insert example users
INSERT INTO Users (username, accent_color_hex_value)
VALUES
    ('user1', '#FF5733'),
    ('user2', '#33FF57'),
    ('user3', '#3357FF');

-- Insert Task Managers for each user
INSERT INTO Task_Managers (user_id)
VALUES
    ((SELECT id FROM Users WHERE username = 'user1')),
    ((SELECT id FROM Users WHERE username = 'user2')),
    ((SELECT id FROM Users WHERE username = 'user3'));

-- Insert Task Directories (Folders) for each user
-- User1
-- Insert Folder1_User1
INSERT INTO Task_Directories (name, task_manager_id)
VALUES ('Folder1_User1', (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user1')));

-- Insert Folder2_User1 with Folder1_User1 as its parent
INSERT INTO Task_Directories (name, parent_id, task_manager_id)
VALUES ('Folder2_User1', LAST_INSERT_ID(), (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user1')));

-- Insert Folder3_User1 (no parent)
INSERT INTO Task_Directories (name, task_manager_id)
VALUES ('Folder3_User1', (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user1')));

-- User2
-- Insert Folder1_User2
INSERT INTO Task_Directories (name, task_manager_id)
VALUES ('Folder1_User2', (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user2')));

-- Insert Folder2_User2 with Folder1_User2 as its parent
INSERT INTO Task_Directories (name, parent_id, task_manager_id)
VALUES ('Folder2_User2', LAST_INSERT_ID(), (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user2')));

-- Insert Folder3_User2 (no parent)
INSERT INTO Task_Directories (name, task_manager_id)
VALUES ('Folder3_User2', (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user2')));

-- User3
-- Insert Folder1_User3
INSERT INTO Task_Directories (name, task_manager_id)
VALUES ('Folder1_User3', (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user3')));

-- Insert Folder2_User3 with Folder1_User3 as its parent
INSERT INTO Task_Directories (name, parent_id, task_manager_id)
VALUES ('Folder2_User3', LAST_INSERT_ID(), (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user3')));

-- Insert Folder3_User3 (no parent)
INSERT INTO Task_Directories (name, task_manager_id)
VALUES ('Folder3_User3', (SELECT task_manager_id FROM Task_Managers WHERE user_id = (SELECT id FROM Users WHERE username = 'user3')));

-- Insert 3 tasks per user, each in a different folder
-- User1 tasks
INSERT INTO Tasks (title, enclosing_folder_id, notes, creation_date, scheduled_execution, due_date, state) VALUES
                                                                                                               ('Task1_User1', (SELECT id FROM Task_Directories WHERE name = 'Folder1_User1'), 'Notes for Task1_User1', NOW(), NOW() + INTERVAL 1 DAY, NOW() + INTERVAL 2 DAY, 0),
                                                                                                               ('Task2_User1', (SELECT id FROM Task_Directories WHERE name = 'Folder2_User1'), 'Notes for Task2_User1', NOW(), NOW() + INTERVAL 2 DAY, NOW() + INTERVAL 3 DAY, 0),
                                                                                                               ('Task3_User1', (SELECT id FROM Task_Directories WHERE name = 'Folder3_User1'), 'Notes for Task3_User1', NOW(), NOW() + INTERVAL 3 DAY, NOW() + INTERVAL 4 DAY, 0);

-- User2 tasks
INSERT INTO Tasks (title, enclosing_folder_id, notes, creation_date, scheduled_execution, due_date, state) VALUES
                                                                                                               ('Task1_User2', (SELECT id FROM Task_Directories WHERE name = 'Folder1_User2'), 'Notes for Task1_User2', NOW(), NOW() + INTERVAL 1 DAY, NOW() + INTERVAL 2 DAY, 0),
                                                                                                               ('Task2_User2', (SELECT id FROM Task_Directories WHERE name = 'Folder2_User2'), 'Notes for Task2_User2', NOW(), NOW() + INTERVAL 2 DAY, NOW() + INTERVAL 3 DAY, 0),
                                                                                                               ('Task3_User2', (SELECT id FROM Task_Directories WHERE name = 'Folder3_User2'), 'Notes for Task3_User2', NOW(), NOW() + INTERVAL 3 DAY, NOW() + INTERVAL 4 DAY, 0);

-- User3 tasks
INSERT INTO Tasks (title, enclosing_folder_id, notes, creation_date, scheduled_execution, due_date, state) VALUES
                                                                                                               ('Task1_User3', (SELECT id FROM Task_Directories WHERE name = 'Folder1_User3'), 'Notes for Task1_User3', NOW(), NOW() + INTERVAL 1 DAY, NOW() + INTERVAL 2 DAY, 0),
                                                                                                               ('Task2_User3', (SELECT id FROM Task_Directories WHERE name = 'Folder2_User3'), 'Notes for Task2_User3', NOW(), NOW() + INTERVAL 2 DAY, NOW() + INTERVAL 3 DAY, 0),
                                                                                                               ('Task3_User3', (SELECT id FROM Task_Directories WHERE name = 'Folder3_User3'), 'Notes for Task3_User3', NOW(), NOW() + INTERVAL 3 DAY, NOW() + INTERVAL 4 DAY, 0);

INSERT INTO Sub_Tasks (main_task_id, title, state) VALUES
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User1'), 'Subtask1_Task1_User1', 0),
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User1'), 'Subtask2_Task1_User1', 0),
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User1'), 'Subtask3_Task1_User1', 0);

-- User2 subtasks
INSERT INTO Sub_Tasks (main_task_id, title, state) VALUES
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User2'), 'Subtask1_Task1_User2', 0),
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User2'), 'Subtask2_Task1_User2', 0),
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User2'), 'Subtask3_Task1_User2', 0);

-- User3 subtasks
INSERT INTO Sub_Tasks (main_task_id, title, state) VALUES
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User3'), 'Subtask1_Task1_User3', 0),
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User3'), 'Subtask2_Task1_User3', 0),
                                                       ((SELECT id FROM Tasks WHERE title = 'Task1_User3'), 'Subtask3_Task1_User3', 0);