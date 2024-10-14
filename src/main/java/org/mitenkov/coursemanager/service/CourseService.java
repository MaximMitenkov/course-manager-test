package org.mitenkov.coursemanager.service;

import lombok.RequiredArgsConstructor;
import org.mitenkov.coursemanager.dto.CourseStatistics;
import org.mitenkov.coursemanager.entity.Course;
import org.mitenkov.coursemanager.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Page<Course> getAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return supplyWithCurrentStudents(courses);
    }

    public Page<Course> getAllActiveCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAllActive(pageable);
        return supplyWithCurrentStudents(courses);
    }

    private Page<Course> supplyWithCurrentStudents(Page<Course> courses) {

        List<Long> ids = courses.getContent().stream().map(Course::getId).toList();
        Map<Long, Long> currentStudentsCounters =  courseRepository.countCurrentStudents(ids).stream()
                .collect(Collectors.toMap(CourseStatistics::courseId, CourseStatistics::numberOfStudents));

        for (Course course : courses) {
            long courseId = course.getId();
            Long studentsCount = currentStudentsCounters.get(courseId);
            course.setCurrentStudentsCount(studentsCount.intValue());
        }
        return courses;
    }

}
