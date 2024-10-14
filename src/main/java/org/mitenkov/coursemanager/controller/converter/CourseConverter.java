package org.mitenkov.coursemanager.controller.converter;

import org.mitenkov.coursemanager.dto.CourseDto;
import org.mitenkov.coursemanager.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    public CourseDto convertToDto(Course course) {
        return CourseDto.builder()
                .currentNumberOfStudents(course.getCurrentStudentsCount())
                .id(course.getId())
                .maxStudents(course.getMaxStudents())
                .name(course.getName())
                .build();
    }
}
