package com.cob.salesforce.entity.validation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Setter
@Getter
public class InsuranceCommercialInfoRequiredEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private Boolean insuranceCompany;
    @Column
    private Boolean memberId;
    @Column
    private Boolean ploicyId;
    @Column
    private Boolean relationship;
    @Column
    private Boolean secondryInsurance;
    @Column
    private Boolean policyHolderFirstName;
    @Column
    private Boolean policyHolderMiddleName;
    @Column
    private Boolean policyHolderLastName;
    @Column
    private Boolean secondryInsuranceCompany;
    @Column
    private Boolean secondryInsuranceMemberId;
    @Column
    private Boolean medicareCoverage;
    @Column
    private Boolean employerFirstName;
    @Column
    private Boolean employerMiddleName;
    @Column
    private Boolean employerLastName;
    @Column
    private Boolean employerPhone;
}
