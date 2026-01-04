INSERT INTO task_completion(date,task_id)
VALUES
    (current_date,1),
    (current_date-1,1),
    (current_date-2,1),
    (current_date-4,1),
    (current_date,3);