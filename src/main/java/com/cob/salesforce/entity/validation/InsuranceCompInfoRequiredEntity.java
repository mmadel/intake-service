package com.cob.salesforce.entity.validation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Setter
@Getter
public class InsuranceCompInfoRequiredEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private Boolean relatedInjury;
    @Column
    private Boolean accidentDate;
    @Column
    private Boolean workerStatus;
    @Column
    private Boolean address;
    @Column
    private Boolean fax;
    @Column
    private Boolean insuranceName;
    @Column
    private Boolean claimNumber;
    @Column
    private Boolean adjusterName;
    @Column
    private Boolean adjusterPhone;
    @Column
    private Boolean attorneyName;
    @Column
    private Boolean attorneyPhone;
    @Column
    private Boolean caseStatus;
}
