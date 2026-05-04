package com.billing.shortcourse.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 50)
    private String admino;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String country;

    private LocalDate dob;
    
    @Column(name = "gender_id")
    private Integer genderId;

    @Column(name = "entry_id")
    private Integer entryId;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "intake_id")
    private Integer intakeId;

    @Column(length = 20)
    private String telephone;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

