package org.mitenkov.coursemanager.helper;

import lombok.RequiredArgsConstructor;
import org.mitenkov.coursemanager.repository.CourseRepository;
import org.mitenkov.coursemanager.repository.EnrollmentRepository;
import org.mitenkov.coursemanager.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DBCleaner {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public void cleanAll() {
        cleanEnrollments();
        cleanCourses();
        cleanStudents();
    }

    public void cleanEnrollments() {
        enrollmentRepository.deleteAll();
    }

    public void cleanStudents() {
        studentRepository.deleteAll();
    }

    public void cleanCourses() {
        courseRepository.deleteAll();
    }

}
