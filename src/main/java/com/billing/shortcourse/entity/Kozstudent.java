package com.billing.shortcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "kozstudents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kozstudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "coursename", nullable = false)
    private String coursename;

    @Column(name = "courseprice", nullable = false)
    private Float courseprice;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "accessed")
    private Boolean accessed;
}