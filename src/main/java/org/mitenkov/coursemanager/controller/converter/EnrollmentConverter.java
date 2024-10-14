package org.mitenkov.coursemanager.controller.converter;

import org.mitenkov.coursemanager.dto.EnrollmentDto;
import org.mitenkov.coursemanager.entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentConverter {

    public EnrollmentDto toDto(Enrollment dto) {
        return EnrollmentDto.builder()
                .id(dto.getId())
                .courseId(dto.getCourse().getId())
                .studentId(dto.getStudent().getId())
                .build();
    }

}
