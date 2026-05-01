package com.billing.hostel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "hosteldetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostelDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "level_id", nullable = false)
    private Integer levelId;

    @Column(name = "intake_id", nullable = false)
    private Integer intakeId;

    @Column(name = "gender_id", nullable = false)
    private Integer genderId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}