CREATE TABLE roles
(
    id      BIGSERIAL                                    NOT NULL,
    name    VARCHAR(100)                                 NOT NULL,
    CONSTRAINT ROLES_PKEY PRIMARY KEY (id),
    UNIQUE (name)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN')
ON CONFLICT DO NOTHING;