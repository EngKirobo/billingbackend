package com.billing.shortcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "coursebookings",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "coursebookings_stud_id_intake_id_unique",
                        columnNames = {"stud_id", "intake_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "intake_id", nullable = false)
    private Integer intakeId;

    @Column(name = "stud_id")
    private Integer studId;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "allowed")
    private Boolean allowed;

    @Column(name = "ctn")
    private Boolean ctn;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}