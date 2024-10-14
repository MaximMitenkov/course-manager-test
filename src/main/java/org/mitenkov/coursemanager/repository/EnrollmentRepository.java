package org.mitenkov.coursemanager.repository;

import org.mitenkov.coursemanager.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Integer countByCourseId(Long courseId);
    boolean existsByStudent_IdAndCourse_Id(Long studentId, Long courseId);
}
