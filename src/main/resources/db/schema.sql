-- CREATE DATABASE IF NOT EXISTS stumanage;
-- USE stumanage;
DROP TABLE IF EXISTS students;

CREATE TABLE IF NOT EXISTS students(
    real_id INTEGER PRIMARY KEY,
    name VARCHAR(30),
    phone VARCHAR(50),
    department VARCHAR(50),
    student_id VARCHAR(30)
);