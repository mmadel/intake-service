package com.cob.salesforce.models.intake.essentials;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientAddress implements Serializable {
    private String type;
    private String first;
    private String second;
    private String country;
    private String state;
    private String province;
    private String city;
    private String zipCode;
}
