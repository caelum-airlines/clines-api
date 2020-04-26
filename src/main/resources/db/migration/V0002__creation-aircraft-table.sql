CREATE TABLE aircraft (
    code VARCHAR(10) NOT NULL,
    model_id BIGINT NOT NULL,

    CONSTRAINT pk_aircraft PRIMARY KEY(code),
    CONSTRAINT fk_aircraft_to_aircraft_models FOREIGN KEY(model_id) REFERENCES aircraft_models(id)
);

INSERT INTO aircraft (code, model_id)
    VALUES  ('BX123AC', 1),
            ('AX123AC', 2);