package com.cob.salesforce.models.intake.essentials;

import com.cob.salesforce.enums.EmploymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientEmployment  implements Serializable {
    private EmploymentStatus employmentStatus;

    private String employmentCompany;
}
