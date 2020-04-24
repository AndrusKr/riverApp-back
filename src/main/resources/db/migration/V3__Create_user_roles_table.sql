CREATE TABLE user_roles
(
    user_id BIGINT,
    role_id BIGINT
);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON UPDATE RESTRICT ON DELETE CASCADE;

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_roles
        FOREIGN KEY (role_id) REFERENCES roles (id)
            ON UPDATE RESTRICT ON DELETE CASCADE;

INSERT INTO user_roles
VALUES (1, 1),
       (1, 2),
       (2, 1);