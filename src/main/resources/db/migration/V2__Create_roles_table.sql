CREATE TABLE roles
(
    id      BIGSERIAL                                    NOT NULL,
    name    VARCHAR(100)                                 NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()    NOT NULL,
    updated TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()    NOT NULL,
    status  VARCHAR(25)                 DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT ROLES_PKEY PRIMARY KEY (id),
    UNIQUE (name)
);
