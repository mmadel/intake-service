package com.cob.salesforce.models.intake;

import com.cob.salesforce.models.intake.agreement.PatientAgreement;
import com.cob.salesforce.models.intake.essentials.*;
import com.cob.salesforce.models.intake.grantor.PatientGrantor;
import com.cob.salesforce.models.intake.insurance.PatientInsurance;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.signature.PatientSignature;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {

    private Long id;
    private PatientEssentialInformation patientEssentialInformation;
    private PatientMedical patientMedical;
    private PatientInsurance patientInsurance;
    private PatientGrantor patientGrantor;
    private PatientSource patientSource;
    private PatientSignature patientSignature;
    private PatientAgreement patientAgreements;

    private Long clinicId;
}
