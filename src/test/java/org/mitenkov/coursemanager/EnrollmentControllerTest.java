package org.mitenkov.coursemanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mitenkov.coursemanager.data.InitDataCreator;
import org.mitenkov.coursemanager.dto.CoursePickRequestDto;
import org.mitenkov.coursemanager.dto.EnrollmentDto;
import org.mitenkov.coursemanager.helper.CourseClient;
import org.mitenkov.coursemanager.helper.DBCleaner;
import org.mitenkov.coursemanager.helper.EnrollmentClient;
import org.mitenkov.coursemanager.repository.CourseRepository;
import org.mitenkov.coursemanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentControllerTest extends BaseTest {

    @Autowired
    DBCleaner dbCleaner;

    @Autowired
    InitDataCreator creator;

    @Autowired
    EnrollmentClient enrollmentClient;

    @Autowired
    CourseClient courseClient;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @BeforeEach
    void beforeEach() {
        dbCleaner.cleanAll();
        creator.create();
    }

    @Test
    void pickCourseCorrectTest() {

        long studentId = studentRepository.findAll().getFirst().getId();
        long courseId = courseRepository.findAll().getFirst().getId();

        CoursePickRequestDto request = new CoursePickRequestDto(studentId, courseId);

        assertEquals(0, courseClient.getCourses().getFirst().currentNumberOfStudents());
        EnrollmentDto result = enrollmentClient.pickCourse(request);
        assertEquals(studentId, result.studentId());
        assertEquals(courseId, result.courseId());
        assertEquals(1, courseClient.getCourses().getFirst().currentNumberOfStudents());
    }

    @Test
    void pickCourseIncorrectTest() {
        long studentId = studentRepository.findAll().getFirst().getId();
        long courseId = courseRepository.findAll().getLast().getId();
        CoursePickRequestDto request = new CoursePickRequestDto(studentId, courseId);

        int status = enrollmentClient.pickCourseGetStatus(request);
        assertEquals(400, status);
        assertEquals(0, courseClient.getCourses().getLast().currentNumberOfStudents());

        courseId = courseRepository.findAll().getFirst().getId();
        request = new CoursePickRequestDto(studentId, courseId);

        status = enrollmentClient.pickCourseGetStatus(request);
        assertEquals(200, status);

        status = enrollmentClient.pickCourseGetStatus(request);
        assertEquals(409, status);
        assertEquals(1, courseClient.getCourses().getFirst().currentNumberOfStudents());

        courseId = 100;
        request = new CoursePickRequestDto(studentId, courseId);

        status = enrollmentClient.pickCourseGetStatus(request);
        assertEquals(404, status);
    }

}
