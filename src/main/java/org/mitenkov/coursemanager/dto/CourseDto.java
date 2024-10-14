package org.mitenkov.coursemanager.dto;

import lombok.Builder;

@Builder
public record CourseDto(
        long id,
        String name,
        int maxStudents,
        int currentNumberOfStudents
) {
}
