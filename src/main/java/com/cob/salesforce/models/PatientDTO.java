
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientDTO {


    private AddressInfoDTO addressInfoDTO;

    private AgreementsDTO agreementsDTO;

    private BasicInfoDTO basicInfo;

    private InsuranceQuestionnaireInfoDTO insuranceQuestionnaireInfoDTO;

    private MedicalHistroyInformationDTO medicalHistroyInformation;

    private MedicalQuestionnaireInfoDTO medicalQuestionnaireInfoDTO;

}
