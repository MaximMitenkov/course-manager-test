package org.mitenkov.coursemanager.data;


import lombok.RequiredArgsConstructor;
import org.mitenkov.coursemanager.entity.Course;
import org.mitenkov.coursemanager.entity.Student;
import org.mitenkov.coursemanager.repository.CourseRepository;
import org.mitenkov.coursemanager.repository.StudentRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDataCreator {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void create() {
        if (studentRepository.count() == 0) {
            createStudents();
        }
        if (courseRepository.count() == 0) {
            createCourses();
        }
    }

    public void createStudents() {
        List<String> names = Arrays.asList("Ivan", "Victor", "Eugene", "Andrew", "Maxim");
        for (String name : names) {
            studentRepository.save(new Student (name));
        }
    }

    public void createCourses() {
        Course course1 = Course.builder()
                .name("Autumn course")
                .currentStudentsCount(0)
                .maxStudents(3)
                .registrationStart(OffsetDateTime.now().minusDays(2))
                .registrationEnd(OffsetDateTime.now().plusDays(1))
                .courseStart(OffsetDateTime.now().plusDays(5))
                .courseEnd(OffsetDateTime.now().plusDays(30))
                .build();

        Course course2 = Course.builder()
                .name("Winter course")
                .currentStudentsCount(0)
                .maxStudents(5)
                .registrationStart(OffsetDateTime.now().plusMonths(2))
                .registrationEnd(OffsetDateTime.now().plusMonths(2).plusDays(1))
                .courseStart(OffsetDateTime.now().plusMonths(2).plusDays(5))
                .courseEnd(OffsetDateTime.now().plusMonths(3))
                .build();

        courseRepository.save(course1);
        courseRepository.save(course2);
    }
}
