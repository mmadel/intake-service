package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientContainerDTO {
    private Long birthDate;
    private String birthDateDate;

    private String email;

    private String emergencyName;

    private String emergencyPhone;

    private String employmentStatus;

    private String firstName;

    private String gender = "";

    private String patientId;

    private String idType;

    private Long idEffectiveFrom;

    private Long idEffectiveTo;

    private String lastName;

    private String maritalStatus;

    private String middleName;

    private String phoneNumber;

    private String phoneType;
    private String country;

    private String first;

    private String second;
    private String state;
    private String province;
    private String city;
    private String type;
    private String zipCode;

    private Long createdAt;
}
