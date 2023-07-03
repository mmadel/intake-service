
package com.cob.salesforce.models;

import com.cob.salesforce.entity.Agreement;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    Long createdAt;


}
