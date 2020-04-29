CREATE TABLE rivers
(
    id                BIGSERIAL UNIQUE                             NOT NULL,

    name              VARCHAR(100)                                 NOT NULL,
    length            INTEGER                                      NOT NULL,
    pool_area         INTEGER                                      NOT NULL,
    water_consumption INTEGER                                      NOT NULL,
    average_slope     FLOAT                                        NULL,
    flow_rate         FLOAT                                        NULL,

    created_by        BIGINT                                       NULL,
    created_at        TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()    NOT NULL,
    updated_by        BIGINT                                       NULL,
    updated_at        TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()    NOT NULL,
    status            VARCHAR(25)                 DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT RIVERS_PKEY PRIMARY KEY (id),
    FOREIGN KEY (created_by) REFERENCES users (id),
    FOREIGN KEY (updated_by) REFERENCES users (id)
);

INSERT INTO rivers(name,
                   length, --km
                   pool_area, --km2
                   water_consumption,--m3/sec
                   average_slope, --m/km
                   flow_rate, --m/sec
                   created_by,
                   updated_by)
VALUES ('Дняпро', 2201, 504000, 1670, 0.09, 0.75, 1, 1),
       ('Бярэзіна', 613, 24500, 145, 0.08, 0.42, 1, 1),
       ('Прыпяць', 775, 114300, 460, 0.08, 0.69, 1, 1),
       ('Сож', 648, 42100, 207, NULL, 1.2, 1, 1),
       ('Нёман', 937, 98200, 678, 0.19, 0.56, 1, 1),
       ('Пціч', 421, 9470, 48, 0.04, 0.5, 1, 1),
       ('Свіслач', 327, 5160, 24, 0.06, 0.42, 1, 1),
       ('Заходняя Дзвіна', 1020, 87900, 678, 0.203, 0.08, 1, 1)
ON CONFLICT DO NOTHING;

