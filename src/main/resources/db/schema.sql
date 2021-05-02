-- CREATE DATABASE IF NOT EXISTS stumanage;
-- USE stumanage;
DROP TABLE IF EXISTS students;

CREATE TABLE IF NOT EXISTS students(
    real_id INTEGER PRIMARY KEY,
    name VARCHAR(30),
    gender INTEGER NOT NULL,
    birth_date DATE,
    native_place VARCHAR(50),
    department VARCHAR(50),
    id VARCHAR(30)
);