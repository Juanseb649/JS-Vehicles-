-- Script para crear la tabla `cars` usada por CarDAO
CREATE TABLE IF NOT EXISTS cars (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    year_car INTEGER NOT NULL,
    num_doors INTEGER NOT NULL,
    pedals VARCHAR(50),
    fuel VARCHAR(50)
);
