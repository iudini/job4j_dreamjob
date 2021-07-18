CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    created TIMESTAMP
);

CREATE TABLE candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    city_id INTEGER REFERENCES city(id)
);
CREATE TABLE dream_user (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT UNIQUE,
    password TEXT
);
CREATE TABLE city (
  id SERIAL PRIMARY KEY,
  name TEXT
);