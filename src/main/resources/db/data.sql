insert into user_tb(username, password, email, created_at) values('ssar', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'ssar@nate.com',  now());
insert into user_tb(username, password, email, created_at) values('cos', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'cos@nate.com', now());
insert into user_tb(username, password, email, created_at) values('love', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'love@nate.com', now());

INSERT INTO todo_tb (title, user_id, category, content, due_date, created_at, repeat, is_completed, is_deleted)
VALUES
    ('Task 1', 1, 'Work', 'Content for Task 1', CURRENT_TIMESTAMP + INTERVAL '1' DAY, CURRENT_TIMESTAMP, '매일', false, false),
    ('Task 2', 1, 'Personal', 'Content for Task 2', CURRENT_TIMESTAMP + INTERVAL '2' DAY, CURRENT_TIMESTAMP, '매주', false, false),
    ('Task 3', 1, 'Work', 'Content for Task 3', CURRENT_TIMESTAMP + INTERVAL '2' DAY, CURRENT_TIMESTAMP, '매월', false, false),
    ('Task 4', 1, 'Study', 'Content for Task 4', CURRENT_TIMESTAMP + INTERVAL '3' DAY, CURRENT_TIMESTAMP, '매년', false, false),
    ('Task 5', 1, 'Exercise', 'Content for Task 5', CURRENT_TIMESTAMP + INTERVAL '5' DAY, CURRENT_TIMESTAMP, '없음', false, false),
    ('Task 6', 1, 'Leisure', 'Content for Task 6', CURRENT_TIMESTAMP + INTERVAL '6' DAY, CURRENT_TIMESTAMP, '매일', false, false),
    ('Task 7', 1, 'Work', 'Content for Task 7', CURRENT_TIMESTAMP + INTERVAL '8' DAY, CURRENT_TIMESTAMP, '매주', false, false),
    ('Task 8', 1, 'Personal', 'Content for Task 8', CURRENT_TIMESTAMP + INTERVAL '8' DAY, CURRENT_TIMESTAMP, '매월', false, false),
    ('Task 9', 1, 'Exercise', 'Content for Task 9', CURRENT_TIMESTAMP + INTERVAL '9' DAY, CURRENT_TIMESTAMP, '매년', false, false),
    ('Task 10', 1, 'Study', 'Content for Task 10', CURRENT_TIMESTAMP + INTERVAL '30' DAY, CURRENT_TIMESTAMP, '없음', false, false);
