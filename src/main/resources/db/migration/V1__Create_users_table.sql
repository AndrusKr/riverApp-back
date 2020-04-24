CREATE TABLE users
(
    id         BIGSERIAL                                    NOT NULL,
    username   VARCHAR(100)                                 NOT NULL,
    email      VARCHAR(255)                                 NOT NULL,
    first_name VARCHAR(100)                                 NOT NULL,
    last_name  VARCHAR(100)                                 NOT NULL,
    password   VARCHAR(255)                                 NOT NULL,
    created_by BIGINT                                       NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()    NOT NULL,
    updated_by BIGINT                                       NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()    NOT NULL,
    status     VARCHAR(25)                 DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT USERS_PKEY PRIMARY KEY (id),
    FOREIGN KEY (created_by) REFERENCES users (id),
    FOREIGN KEY (updated_by) REFERENCES users (id),
    UNIQUE (email),
    UNIQUE (username)
);
