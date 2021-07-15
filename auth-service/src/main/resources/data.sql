INSERT INTO roles
VALUES(1, 'ROLE_USER');

INSERT INTO auth_users (user_id, fullname, username, password)
VALUES('101', 'Rezaur Rahman', 'DevRezaur', 'iamadmin');

INSERT INTO auth_users (user_id, fullname, username, password)
VALUES('102', 'Sanzida Sultana', 'SanzidaSultana', 'iamuser');

INSERT INTO auth_user_role
VALUES('101', 1);

INSERT INTO auth_user_role
VALUES('102', 1);