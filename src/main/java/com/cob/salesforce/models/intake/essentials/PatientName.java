package com.cob.salesforce.models.intake.essentials;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientName implements Serializable {

    private String firstName;
    private String middleName;
    private String lastName;
}
