CREATE TABLE locations (
    id BIGSERIAL,
    country CHAR(2),
    state CHAR(2),
    city VARCHAR(100),

    CONSTRAINT pk_locations PRIMARY KEY (id),
    CONSTRAINT unq_locations UNIQUE (country, state, city)
);

INSERT INTO locations(country, state, city)
    VALUES  ('BR', 'SP', 'Guarulhos'),
            ('BR', 'SP', 'São Paulo'),
            ('BR', 'SP', 'Campinas'),
            ('BR', 'RJ', 'Rio de Janeiro'),
            ('BR', 'RJ', 'Galeão'),
            ('BR', 'RS', 'Porto Alegre'),
            ('BR', 'RN', 'Natal'),
            ('BR', 'PE', 'Recife'),
            ('BR', 'PR', 'Curitiba'),
            ('BR', 'MG', 'Belo Horizonte'),
            ('BR', 'MT', 'Cuibá'),
            ('BR', 'CE', 'Fortaleza'),
            ('BR', 'DF', 'Brasília'),
            ('BR', 'BA', 'Salvador'),
            ('BR', 'AM', 'Manaus');
