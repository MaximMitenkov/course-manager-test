package org.mitenkov.coursemanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int maxStudents;
    private OffsetDateTime registrationStart;
    private OffsetDateTime registrationEnd;
    private OffsetDateTime courseStart;
    private OffsetDateTime courseEnd;

    @Transient
    private int currentStudentsCount;
}
