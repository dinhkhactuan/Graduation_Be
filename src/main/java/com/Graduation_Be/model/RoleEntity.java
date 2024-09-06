package com.Graduation_Be.model;

import com.Graduation_Be.shard.baseModel.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    Long roleId;

    @Column(name = "description")
    String description;

    @Column(name = "roleCode")
    String roleCode;

    @OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    List<UserEntity> users;
}
