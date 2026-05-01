package com.billing.auth.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = "email")
    },
    indexes = {
        @Index(name = "idx_email", columnList = "email")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    @Builder.Default
    private Boolean enabled = true;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    // 🔑 MANY USERS → ONE ROLE
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "role_id",
        foreignKey = @ForeignKey(name = "fk_user_role")
    )
    private Role role;
}