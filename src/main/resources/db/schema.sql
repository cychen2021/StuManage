DROP TABLE IF EXISTS students;

CREATE TABLE IF NOT EXISTS students(
    real_id INTEGER PRIMARY KEY NOT NULL,
    name VARCHAR(30),
    gender VARCHAR(30),
    birth_date DATE,
    native_place VARCHAR(50),
    department VARCHAR(50),
    student_id VARCHAR(30)
);