package com.cob.salesforce.entity;

import com.cob.salesforce.enums.CaseStatus;
import com.cob.salesforce.enums.WorkerStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity(name = "insurance_worker_comp_no_fault")
@Getter
@Setter
public class InsuranceWorkerCompNoFault extends PatientDependencyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "injury_type")
    private String injuryType;
    @Column(name = "accident_date")
    private Long accidentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "worker_status")
    private WorkerStatus workerStatus;

    @Column(name = "address")
    private String address;

    @Column(name = "fax")
    private String fax;

    @Column(name = "insurance_name")
    private String insuranceName;
    @Column(name = "claim_number")
    private BigInteger claimNumber;
    @Column(name = "adjuster_name")
    private String adjusterName;

    @Column(name = "adjuster_phone")
    private String adjusterPhone;
    @Column(name = "attorney_name")
    private String attorneyName;

    @Column(name = "attorney_phone")
    private String attorneyPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_status")
    private CaseStatus caseStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
