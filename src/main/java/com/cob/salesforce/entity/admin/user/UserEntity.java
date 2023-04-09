package com.cob.salesforce.entity.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.enums.admin.UserRole;
import com.cob.salesforce.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "user_address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<ClinicEntity> clinics;
    @Column
    private Long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = DateUtil.removeTime(new Date()).getTime();
    }


}
