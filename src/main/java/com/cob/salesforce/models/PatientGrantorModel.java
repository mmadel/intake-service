package com.cob.salesforce.models;

import com.cob.salesforce.enums.EmergencyRelation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientGrantorModel {
    private Long id;

    private String firstName;


    private String middleName;


    private String lastName;


    private EmergencyRelation relation;

}
