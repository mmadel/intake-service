package com.cob.salesforce.entity;

import com.cob.salesforce.enums.Relationship;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "insurance_worker_commercial")
@Getter
@Setter
public class InsuranceWorkerCommercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "insurance_company_Id")
    private Long insuranceCompanyId;
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "policyId")
    private Long policyId;

    @Enumerated(EnumType.STRING)
    @Column(name="relationship")
    private Relationship relationship;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secondary_insurance_id", referencedColumnName = "id")
    private SecondaryInsurance secondaryInsurance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_relationship_id", referencedColumnName = "id")
    private PatientRelationship patientRelationship;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicare_coverage_id", referencedColumnName = "id")
    private MedicareCoverage medicareCoverage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
