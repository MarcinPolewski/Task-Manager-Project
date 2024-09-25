INSERT INTO Users(username, accent_color_hex_value)
VALUES
("Jórek Ogórek", "#112233"),
("Joe Doe", "#555555"),
("Johanes Bohanes", "#359735");

INSERT INTO Task_Managers(user_id)
VALUES
(1),(2),(3);

INSERT INTO Tasks (task_manager_id, title, notes, creation_data, scheduled_execution, due_date, state) 
VALUES 
    (1, 'Complete project report', 'Finalizing the report for the client', NOW(), '2024-09-27 09:00:00', '2024-09-30 17:00:00', 0), -- Task 1 for user_id 1
    (1, 'Prepare for meeting', 'Gather all the necessary documents and prepare the presentation', NOW(), '2024-09-28 14:00:00', '2024-09-28 17:00:00', 0), -- Task 2 for user_id 1
    (1, 'Team feedback', 'Collect and compile team feedback for the quarterly review', NOW(), '2024-09-29 10:00:00', '2024-09-29 15:00:00', 0), -- Task 3 for user_id 1
    (2, 'Budget review', 'Review the budget for the upcoming project', NOW(), '2024-09-26 13:00:00', '2024-09-26 18:00:00', 1); -- Task 4 for user_id 2

INSERT INTO Sub_Tasks (main_task_id, title, state)
VALUES
	(1, "this is sub task number 1", 0),
    (1, "this is sub task number 2", 0);