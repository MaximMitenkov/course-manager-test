package org.mitenkov.coursemanager.dto;

import jakarta.validation.constraints.Positive;

public record CoursePickRequestDto(
        @Positive
        long studentId,
        @Positive
        long courseId
) {
}
