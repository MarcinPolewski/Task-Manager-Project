CREATE DATABASE  IF NOT EXISTS `task_manager`;
USE `task_manager`;

CREATE TABLE Users(
    id INT AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    accent_color_hex_value CHAR(7),     -- for instance #112233
    PRIMARY KEY (id)
);

CREATE TABLE Task_Managers(
    task_manager_id INT AUTO_INCREMENT,
    user_id INT,
    PRIMARY KEY (task_manager_id),
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Tasks(
       id INT AUTO_INCREMENT,
       task_manager_id INT,
       title VARCHAR(255),
       notes TEXT,
       creation_date DATETIME,
       scheduled_execution DATETIME,
       due_date DATETIME,
       state INT,
       PRIMARY KEY (id),
       FOREIGN KEY (task_manager_id) REFERENCES Task_Managers(task_manager_id)
);

CREATE TABLE Sub_Tasks(
    id INT AUTO_INCREMENT,
    main_task_id INT,
    title VARCHAR(255),
    state INT,
    PRIMARY KEY (id),
    FOREIGN KEY (main_task_id) REFERENCES Tasks(id)
);