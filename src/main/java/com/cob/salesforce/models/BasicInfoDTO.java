
package com.cob.salesforce.models;

import com.cob.salesforce.enums.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasicInfoDTO {


    private Long birthDate;
    private String birthDateDate;

    private String email;

    private String emergencyName;

    private String emergencyPhone;

    private String employmentStatus;

    private String employmentCompany;
    private EmergencyRelation emergencyRelation;

    private String firstName;

    private String gender = "";

    private String patientId;

    //private String idType;

    //private Long idEffectiveFrom;

    //private Long idEffectiveTo;

    private String lastName;

    private String maritalStatus;

    private String middleName;

    private String phoneNumber;

    private String phoneType;

    private PatientGrantorModel pateintGrantor;


    public String getFullName() {
        return getFirstName() + ',' + getMiddleName()
                + ',' + getLastName();
    }

    public Gender getGenderEnum() {
        return gender =="" ? null :Gender.valueOf(gender);
    }

    public PhoneType getPhoneTypeEnum() {
        return phoneType == "" ? null : PhoneType.valueOf(phoneType);
    }

//    public IDType getIDTypeEnum() {
//
//        return idType == "" ? null : IDType.valueOf(idType);
//    }

    public EmploymentStatus getEmploymentStatusEnum() {
        return employmentStatus == "" ? null : EmploymentStatus.valueOf(employmentStatus);
    }

    public MaritalStatus getMaritalStatusEnum() {
        return maritalStatus == "" ? null : MaritalStatus.valueOf(maritalStatus);
    }
}
