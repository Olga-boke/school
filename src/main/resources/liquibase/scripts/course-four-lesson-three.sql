-- liquibase formatted sql


-- changeset oKrivobokova:1

CREATE INDEX student_name_index ON student (name);

-- changeset oKrivobokova:2

CREATE INDEX faculty_name_color_index ON faculty (name, color);




