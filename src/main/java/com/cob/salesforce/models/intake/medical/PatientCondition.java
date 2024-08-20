package com.cob.salesforce.models.intake.medical;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientCondition implements Serializable {
    private String name;
}
