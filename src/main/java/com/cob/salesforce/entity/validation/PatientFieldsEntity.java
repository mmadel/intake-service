package com.cob.salesforce.entity.validation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "patient_fields")
@Setter
@Getter
public class PatientFieldsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basie_info_id", referencedColumnName = "id")
    private BasicInfoEntity basicInfo;
}
