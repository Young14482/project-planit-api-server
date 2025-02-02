insert into user_tb(username, password, email, created_at, is_deleted) values('ssar', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'ssar@nate.com', CURRENT_TIMESTAMP, false);
insert into user_tb(username, password, email, created_at, is_deleted) values('cos', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'cos@nate.com', CURRENT_TIMESTAMP, false);
insert into user_tb(username, password, email, created_at, is_deleted) values('love', '$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy', 'love@nate.com', CURRENT_TIMESTAMP, false);

INSERT INTO todo_tb (title, user_id, memo, due_date, created_at, repeat, is_completed, is_deleted)
VALUES
    ('Task 1', 1, 'Memo for Task 1', DATEADD(DAY, -1, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매일', false, false),
    ('Task 2', 1, 'Memo for Task 2', DATEADD(DAY, -2, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매주', true, false),
    ('Task 3', 1, 'Memo for Task 3', DATEADD(DAY, -3, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매월', false, false),
    ('Task 4', 1, 'Memo for Task 4', DATEADD(DAY, 0, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매년', false, false),
    ('Task 5', 1, 'Memo for Task 5', DATEADD(DAY, 0, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '없음', false, false),
    ('Task 6', 1, 'Memo for Task 6', DATEADD(DAY, 0, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매일', true, false),
    ('Task 7', 1, 'Memo for Task 7', DATEADD(DAY, -2, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매주', false, false),
    ('Task 8', 1, 'Memo for Task 8', DATEADD(DAY, -1, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매월', true, false),
    ('Task 9', 1, 'Memo for Task 9', DATEADD(DAY, -4, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매년', true, false),
    ('Task 10', 1, 'Memo for Task 10', DATEADD(DAY, 30, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '없음', false, false),
    ('Task 11', 1, 'Memo for Task 11', DATEADD(DAY, 4, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '매년', false, false),
    ('Task 12', 1, 'Memo for Task 12', DATEADD(DAY, 0, CURRENT_TIMESTAMP), CURRENT_TIMESTAMP, '없음', false, false);

INSERT INTO category_tb (name, user_id)
VALUES
    ('작업',1),
    ('생일',1),
    ('운동',1),
    ('공부',1),
    ('하루루틴',1),
    ('주말루틴',1),
    ('한달루틴',1),
    ('카테고리',1),
    ('기타',1);
