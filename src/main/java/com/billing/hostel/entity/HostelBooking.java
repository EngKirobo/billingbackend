package com.billing.hostel.entity;

// import com.billing.shortcourse.entity.Student;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "hostelbookings")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HostelBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "room_id", nullable = false)
    private Integer roomId;

        // ✅ Student relation (cross module)
    @Column(name = "stud_id", nullable = false)
    private Integer studentId;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(nullable = false)
    private String semester;

    private Boolean verified;
    private Boolean allowed;
    private Boolean ctn;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}