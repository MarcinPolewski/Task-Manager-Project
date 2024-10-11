DROP SCHEMA IF EXISTS `task_manager`;
CREATE DATABASE  IF NOT EXISTS `task_manager`;
USE `task_manager`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE Users(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    accent_color_hex_value CHAR(7),     -- for instance #112233
    PRIMARY KEY (id)
);

CREATE TABLE Task_Managers(
    task_manager_id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    PRIMARY KEY (task_manager_id),

    -- add index
    KEY `FK_USER_id` (`user_id`),

    -- add foreign key
    CONSTRAINT `FK_USER` FOREIGN KEY(`user_id`)
    REFERENCES `Users`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE Task_Directories(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    parent_id INT,
    task_manager_id INT NOT NULL,

    PRIMARY KEY(id),

--     constraint and intex for task manager key
    KEY `TASK_MANAGER_FOREIGN_KEY_ID` (`task_manager_id`),

    CONSTRAINT `TASK_MANAGER_FOREIGN_KEY_IN_DIRECTORY` FOREIGN KEY (`task_manager_id`)
    REFERENCES `Task_Managers` (`task_manager_id`)
    ON DELETE  NO ACTION
    ON UPDATE  NO ACTION,

    KEY `PARENT_DIRECTORY_FOREIGN_KEY_ID` (`parent_id`),

    CONSTRAINT `PARENT_DIRECTORY_FOREIGN_KEY` FOREIGN KEY (`parent_id`)
        REFERENCES `Task_Directories` (`id`)
        ON DELETE  NO ACTION
        ON UPDATE  NO ACTION
);

CREATE TABLE Tasks(
       id INT AUTO_INCREMENT,
       title VARCHAR(255),
       enclosing_folder_id INT,
       notes TEXT,
       creation_date DATETIME,
       scheduled_execution DATETIME,
       due_date DATETIME,
       state INT,

       PRIMARY KEY (id),

        KEY `TASK_DIRECTORY_ID` (`enclosing_folder_id`),

        CONSTRAINT `TASK_DIRECTORY_FOREIGN_KEY` FOREIGN KEY (`enclosing_folder_id`)
        REFERENCES `Task_Directories` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE Sub_Tasks(
    id INT AUTO_INCREMENT,
    main_task_id INT,
    title VARCHAR(255),
    state INT,
    PRIMARY KEY (id),
    FOREIGN KEY (main_task_id) REFERENCES Tasks(id)
);

SET FOREIGN_KEY_CHECKS = 1;