package com.cob.salesforce.entity;

import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.enums.EmergencyRelation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "patient_grantor")
@Getter
@Setter
public class PatientGrantor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation")
    private EmergencyRelation relation;

    @Column(name = "id_front", length = 100000)
    private byte[] idFront;

    @Column(name = "id_back", length = 100000)
    private byte[] idBack;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;
}
