
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientDTO {


    private AddressInfoDTO addressInfo;

    private AgreementsDTO agreementsDTO;

    private BasicInfoDTO basicInfo;

    private InsuranceQuestionnaireInfo insuranceQuestionnaireInfo;

    private MedicalHistoryInformationDTO medicalHistoryInformation;

    private MedicalQuestionnaireInfoDTO medicalQuestionnaireInfo;



}
