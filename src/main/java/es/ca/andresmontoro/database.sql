CREATE DATABASE personas OWNER postgres;

CREATE TABLE persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    edad INT
);
