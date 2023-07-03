package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicareCoverageDTO {
    private String employerName;
    private String employerFirstName;
    private String employerMeddileName;
    private String  employerLastName;
    private String employerPhone;

    public String getFullName() {
        return employerFirstName + ',' + employerMeddileName
                + ',' + employerLastName;
    }
}
