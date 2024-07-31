package com.cob.salesforce.models.intake.agreement;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientAgreement implements Serializable {
    Boolean acceptReleaseAgreements;
    Boolean  acceptFinancialResponsibilityAgreements;
    Boolean acceptFinancialAgreementAgreements;
    Boolean acceptInsuranceAgreement;
    Boolean acceptHIPAAAgreements;
    Boolean cancellationPolicyAgreements;
    Boolean communicationAttestationAgreements;
    Boolean authorizationToReleaseObtainInformationAgreements;
    Boolean consentToTreatmentAgreements;
    Boolean noticeOfPrivacyPracticesAgreements;
    Boolean insuranceEligibilityAgreements;
    Boolean assignmentReleaseOfBenefitsAgreements;
    Boolean acceptCuppingAgreements;
    Boolean acceptPelvicAgreements;
    Boolean acceptPhotoVideoAgreements;
}
