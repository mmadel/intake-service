package com.cob.salesforce.entity.admin;

import com.cob.salesforce.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class ClinicEntity {
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

    @PrePersist
    private void beforeSaving() {
        createdAt = DateUtil.removeTime(new Date()).getTime();
    }
}
