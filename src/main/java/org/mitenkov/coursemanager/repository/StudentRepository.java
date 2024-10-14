package org.mitenkov.coursemanager.repository;

import org.mitenkov.coursemanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
