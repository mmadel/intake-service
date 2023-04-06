package com.cob.salesforce.models.pdf;

import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.MaritalStatus;
import com.cob.salesforce.enums.Relationship;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PatientData {
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private Relationship relationship;
    private String dateOfBirth;

    private String patientId;

    private String phone;

    private String email;

    private String address;
    private MaritalStatus maritalStatus;

    private String emergencyFirstName;
    private String emergencyMiddleName;
    private String emergencyLastName;

    private String emergencyPhone;

    private MedicalData medicalData;

    private PatientSourceData patientSourceData;

    private InsuranceData insuranceData;

}
