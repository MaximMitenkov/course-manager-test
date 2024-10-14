package org.mitenkov.coursemanager.service.validator;

import lombok.RequiredArgsConstructor;
import org.mitenkov.coursemanager.entity.Course;
import org.mitenkov.coursemanager.entity.Student;
import org.mitenkov.coursemanager.enums.ErrorCode;
import org.mitenkov.coursemanager.exception.ErrorCodeException;
import org.mitenkov.coursemanager.repository.EnrollmentRepository;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class CoursePickValidator {

    private final EnrollmentRepository enrollmentRepository;

    public void validate(Student student, Course course) {

        long courseId = course.getId();

        if (enrollmentRepository.existsByStudent_IdAndCourse_Id(student.getId(), courseId)) {
            throw new ErrorCodeException(ErrorCode.DUPLICATES_NOT_ALLOWED);
        }

        OffsetDateTime now = OffsetDateTime.now();
        if (course.getRegistrationStart().isAfter(now) || course.getRegistrationEnd().isBefore(now)) {
            throw new ErrorCodeException(ErrorCode.ILLEGAL_TIME);
        }

        Integer currentStudents = enrollmentRepository.countByCourseId(courseId);

        if (currentStudents != null && currentStudents >= course.getMaxStudents()) {
            throw new ErrorCodeException(ErrorCode.COURSE_IS_FULL);
        }
    }
}
