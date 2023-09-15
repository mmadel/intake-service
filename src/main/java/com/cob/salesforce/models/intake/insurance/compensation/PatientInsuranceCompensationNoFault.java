package com.cob.salesforce.models.intake.insurance.compensation;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientInsuranceCompensationNoFault implements Serializable {
    private String injuryType;
    private Long accidentDate;

    private String workerStatus;

    private String fax;

    private String address;

    private String insuranceName;

    private String claimNumber;

    private String adjusterInfoName;

    private String adjusterInfoPhone;

    private String attorneyInfoName;


    private String attorneyInfoPhone;


    private String caseStatus;
}
