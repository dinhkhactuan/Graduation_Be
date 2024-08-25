package com.Graduation_Be.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    Long roleId;

    @Column(name = "description")
    String description;

    @Column(name = "roleCode")
    String roleCode;

    @OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL)
    List<UserEntity> users;
}
