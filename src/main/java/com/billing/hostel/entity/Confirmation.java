package com.billing.hostel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "confirmations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payid;

    @Column(name = "Bill_Id", nullable = false, length = 20)
    private String billId;

    @Column(name = "Full_Name", length = 100)
    private String fullName;

    @Column(name = "PayRefId", length = 50)
    private String payRefId;

    @Column(name = "Control_Number", unique = true, length = 20)
    private String controlNumber;

    @Column(name = "Pdates")
    private LocalDate pdates;

    @Column(name = "Amount_Paid", nullable = false)
    private BigDecimal amountPaid;

    @Column(name = "Pay_Method", nullable = false, length = 100)
    private String payMethod;

    @Column(name = "Receipt_No", length = 50)
    private String receiptNo;
}