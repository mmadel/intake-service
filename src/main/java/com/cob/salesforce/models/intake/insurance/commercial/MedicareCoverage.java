package com.cob.salesforce.models.intake.insurance.commercial;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MedicareCoverage implements Serializable {
    private String employerName;
    private String employerFirstName;
    private String employerMiddleName;
    private String  employerLastName;
    private String employerPhone;
}
