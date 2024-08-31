package com.Graduation_Be.model;

import com.Graduation_Be.shard.baseModel.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    Long userId;

    @Column(name = "userName")
    String userName;

    @Column(name = "userPassword")
    String userPassword;

    @Column(name = "phoneNumber")
    String phoneNumber;

    @Column(name = "email")
    String email;

    @Column(name = "address")
    String address;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private RoleEntity roleEntity;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<AdvertisementEntity> advertisementEntities;

}
