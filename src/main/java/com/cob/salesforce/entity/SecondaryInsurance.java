package com.cob.salesforce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "secondary_insurance")
@Getter
@Setter
public class SecondaryInsurance extends PatientDependencyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "policyholder_name")
    private String policyHolderName;
    @Column(name = "insurance_companyname")
    private String insuranceCompanyName;

    @Column(name = "member_id")
    private String memberId;
}

