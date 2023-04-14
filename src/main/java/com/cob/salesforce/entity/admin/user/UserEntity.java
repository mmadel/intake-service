package com.cob.salesforce.entity.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.enums.admin.UserRole;
import com.cob.salesforce.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String name;
    @Column(name = "password")
    private String password;

    @Column(name = "user_address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_clinic",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private Set<ClinicEntity> clinics;
    @Column
    private Long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = DateUtil.removeTime(new Date()).getTime();
    }


}
