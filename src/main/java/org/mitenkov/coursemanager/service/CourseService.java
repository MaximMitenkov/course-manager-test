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
        List<CourseStatistics> currentStudents = courseRepository.countCurrentStudentsAll();
        return supplyWithCurrentStudents(courses, currentStudents);
    }

    public Page<Course> getAllActiveCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAllActive(pageable);
        List<CourseStatistics> currentStudents = courseRepository.countCurrentStudentsActive();
        return supplyWithCurrentStudents(courses, currentStudents);
    }

    private Page<Course> supplyWithCurrentStudents(Page<Course> courses, List<CourseStatistics> currentStudents) {

        Map<Long, Long> currentStudentsCounters = currentStudents.stream()
                .collect(Collectors.toMap(CourseStatistics::courseId, CourseStatistics::numberOfStudents));

        for (Course course : courses) {
            long courseId = course.getId();
            Long studentsCount = currentStudentsCounters.get(courseId);
            if (studentsCount != null) {
                course.setCurrentStudentsCount(studentsCount.intValue());
            } else {
                course.setCurrentStudentsCount(0);
            }
        }
        return courses;
    }

}
