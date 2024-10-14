package org.mitenkov.coursemanager.dto;

import lombok.Builder;

@Builder
public record EnrollmentDto(
        long id,
        long studentId,
        long courseId
) {
}
