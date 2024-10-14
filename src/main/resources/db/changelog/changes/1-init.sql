CREATE TABLE student
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE course
(
    id                 BIGSERIAL PRIMARY KEY,
    name               VARCHAR(100)             NOT NULL,
    registration_start TIMESTAMP WITH TIME ZONE NOT NULL,
    registration_end   TIMESTAMP WITH TIME ZONE NOT NULL,
    course_start       TIMESTAMP WITH TIME ZONE NOT NULL,
    course_end         TIMESTAMP WITH TIME ZONE NOT NULL,
    max_students       INTEGER                  NOT NULL
);

CREATE TABLE course_enrollment
(
    id         BIGSERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL REFERENCES student (id),
    course_id  BIGINT NOT NULL REFERENCES course (id)
)
