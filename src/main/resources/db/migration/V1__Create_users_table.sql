CREATE TABLE users
(
    id         BIGSERIAL                                    NOT NULL,
    username   VARCHAR(100)                                 NOT NULL,
    email      VARCHAR(255)                                 NOT NULL,
    first_name VARCHAR(100)                                 NOT NULL,
    last_name  VARCHAR(100)                                 NOT NULL,
    password   VARCHAR(255)                                 NOT NULL,
    created    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()    NOT NULL,
    updated    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()    NOT NULL,
    status     VARCHAR(25)                 DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT USERS_PKEY PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (username)
);
