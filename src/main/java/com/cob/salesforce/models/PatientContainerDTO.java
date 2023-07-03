package com.cob.salesforce.models;

import com.cob.salesforce.enums.EmergencyRelation;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientContainerDTO {

    private  Long tableId;
    private Long birthDate;
    private String birthDateDate;

    private String email;

    private String emergencyName;

    private String emergencyPhone;

    private EmergencyRelation emergencyRelation;

    private String employmentStatus;
    private String employmentCompany;

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

    private PatientSourceType patientSourceType;

    private InsuranceWorkerType insuranceWorkerType;

    private Boolean hasPhysicalTherapy;

    private String primaryDoctor;
    private String doctorName;
    private String doctorNPI;
    private String entitySourceData;
}
