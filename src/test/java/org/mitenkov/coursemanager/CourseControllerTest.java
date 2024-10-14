package org.mitenkov.coursemanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mitenkov.coursemanager.data.InitDataCreator;
import org.mitenkov.coursemanager.dto.CourseDto;
import org.mitenkov.coursemanager.helper.CourseClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseControllerTest extends BaseTest {

    @Autowired
    CourseClient courseClient;

    @Autowired
    InitDataCreator creator;

    @BeforeEach
    public void beforeEach() {
        dbCleaner.cleanAll();
        creator.create();
    }

    @Test
    void getCoursesTest() {
        List<CourseDto> courses = courseClient.getCourses(false, 200);
        assertEquals(2, courses.size());
    }

    @Test
    void getCourseWithParameterTest() {
        List<CourseDto> courses = courseClient.getCourses(true, 200);
        assertEquals(1, courses.size());
        assertEquals("Autumn course", courses.getFirst().name());
    }



}
