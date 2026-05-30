package com.billing.shortcourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "kozpaysdetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kozpaysdetails {

    @Id
    @Column(name = "paysid")
    private Integer paysid;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "intakes_id")
    private Integer intakesId;

    @Column(name = "intake_id")
    private Integer intakeId;

    @Column(name = "stud_id")
    private Integer studId;

    @Column(name = "name")
    private String name;

    @Column(name = "controlnumber")
    private String controlnumber;

    @Column(name = "Amount_Paid")
    private BigDecimal amountPaid;
}