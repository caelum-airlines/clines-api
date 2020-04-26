CREATE TABLE airports (
    code CHAR(3) NOT NULL,
    location_id BIGINT NOT NULL,

    CONSTRAINT pk_airports PRIMARY KEY(code),
    CONSTRAINT fk_airports_to_location FOREIGN KEY (location_id) REFERENCES locations(id)
);

INSERT INTO airports (code, location_id)
    VALUES  ('GRU', 1),
            ('CGH', 2),
            ('VCP', 3);