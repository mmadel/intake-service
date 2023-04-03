
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PatientDTO {

    private Long Id;
    private AddressInfoDTO addressInfo;

    private AgreementsDTO agreementsDTO;

    private BasicInfoDTO basicInfo;

    private InsuranceQuestionnaireInfo insuranceQuestionnaireInfo;

    private MedicalHistoryInformationDTO medicalHistoryInformation;

    private MedicalQuestionnaireInfoDTO medicalQuestionnaireInfo;



}
