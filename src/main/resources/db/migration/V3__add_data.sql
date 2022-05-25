INSERT INTO project (name, start_date, manager_id)
VALUES ('Maniculus', NOW(), 0);
INSERT INTO task (project_id, name, start_date, assignee_id)
VALUES (0, 'Structural plan', NOW(), 0);
INSERT INTO task (project_id, name, assignee_id)
VALUES (0, 'Features design', 0);