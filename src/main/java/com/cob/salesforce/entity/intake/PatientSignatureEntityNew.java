package com.cob.salesforce.entity.intake;

import javax.persistence.*;

@Entity
@Table(name = "newpatient_signature")
public class PatientSignatureEntityNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "signature", unique = false, nullable = false, length = 100000)
    private byte[] signature;
    private Long patientId;
}
