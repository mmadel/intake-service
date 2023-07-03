package com.cob.salesforce.entity.validation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Setter
@Getter
public class MedicalHistoryInfoRequiredEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    Boolean height;
    @Column
    Boolean weight;
    @Column
    Boolean evaluationReason;
    @Column
    Boolean medicationPrescription;
    @Column
    Boolean patientCondition;
    @Column
    Boolean scanningTest;
    @Column
    Boolean scanningTestValue;
    @Column
    Boolean metalImplantation;
    @Column
    Boolean pacemaker;
    @Column
    Boolean surgeriesList;
}
