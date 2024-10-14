package org.mitenkov.coursemanager.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mitenkov.coursemanager.dto.CoursePickRequestDto;
import org.mitenkov.coursemanager.entity.Course;
import org.mitenkov.coursemanager.entity.Enrollment;
import org.mitenkov.coursemanager.entity.Student;
import org.mitenkov.coursemanager.enums.ErrorCode;
import org.mitenkov.coursemanager.exception.ErrorCodeException;
import org.mitenkov.coursemanager.repository.CourseRepository;
import org.mitenkov.coursemanager.repository.EnrollmentRepository;
import org.mitenkov.coursemanager.repository.StudentRepository;
import org.mitenkov.coursemanager.service.validator.CoursePickValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class EnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CoursePickValidator validator;

    @Transactional
    public Enrollment pickCourse(@Valid CoursePickRequestDto request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new ErrorCodeException(ErrorCode.NO_SUCH_ELEMENT));
        Course course = courseRepository.findByIdWithLock(request.courseId())
                .orElseThrow(() -> new ErrorCodeException(ErrorCode.NO_SUCH_ELEMENT));
        validator.validate(student, course);
        return enrollmentRepository.save(new Enrollment(0, student, course));
    }
}
