package org.mitenkov.coursemanager.repository;

import jakarta.persistence.LockModeType;
import org.mitenkov.coursemanager.dto.CourseStatistics;
import org.mitenkov.coursemanager.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Course c where c.id = :id")
    Optional<Course> findByIdWithLock(@Param("id") long id);

    @Query("""
        select new org.mitenkov.coursemanager.dto.CourseStatistics(c.id, count(e.id))
        from Course c left join Enrollment e on e.course.id = c.id
        group by c.id""")
    List<CourseStatistics> countCurrentStudentsAll();

    @Query("""
        select new org.mitenkov.coursemanager.dto.CourseStatistics(c.id, count(e.id))
        from Course c left join Enrollment e on e.course.id = c.id
        where current_timestamp between c.registrationStart and c.registrationEnd
        group by c.id""")
    List<CourseStatistics> countCurrentStudentsActive();

    @Query(value ="""
        select * from course where now() between registration_start and registration_end
        """, nativeQuery = true)
    Page<Course> findAllActive(Pageable pageable);
}
