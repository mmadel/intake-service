
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
public class PatientDTO {

    private Long Id;
    private AddressInfoDTO addressInfo;

    private BasicInfoDTO basicInfo;

    private InsuranceQuestionnaireInfo insuranceQuestionnaireInfo;

    private MedicalHistoryInformationDTO medicalHistoryInformation;

    private MedicalQuestionnaireInfoDTO medicalQuestionnaireInfo;

    private Long clinicId;

    AgreementsDTO agreements;

    Map<String, String> patientAgreement = new LinkedHashMap<>();
    PatientSignatureDTO patientSignature;


    Long createdAt;

}
