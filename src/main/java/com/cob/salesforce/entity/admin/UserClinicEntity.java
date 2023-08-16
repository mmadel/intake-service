package com.cob.salesforce.entity.admin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class UserClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "clinic_id")
    private Long clinicId;

    @Column(name = "user_id")
    private String userId;


}
