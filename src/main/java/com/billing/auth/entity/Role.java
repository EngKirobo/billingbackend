package com.billing.auth.entity;



import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    // Optional: reverse mapping (not required but useful)
    // @OneToMany(mappedBy = "role")
    // private Set<User> users;

        
    @OneToMany(mappedBy = "role")
    @JsonIgnore   // 🔥 BREAKS THE LOOP
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
    name = "role_permissions",
    joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id")
)

private Set<Permission> permissions = new java.util.HashSet<>();
}
