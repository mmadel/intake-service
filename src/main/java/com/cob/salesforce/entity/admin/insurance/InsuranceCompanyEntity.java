package com.cob.salesforce.entity.admin.insurance;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class InsuranceCompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "clinic_name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column
    private Long createdAt;

    @OneToMany()
    private List<ClinicEntity> clinics = new ArrayList<>();
    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}
