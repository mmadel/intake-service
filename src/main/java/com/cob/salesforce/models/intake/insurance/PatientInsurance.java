package com.cob.salesforce.models.intake.insurance;

import com.cob.salesforce.models.intake.insurance.commercial.PatientCommercialInsurance;
import com.cob.salesforce.models.intake.insurance.compensation.PatientInsuranceCompensationNoFault;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientInsurance implements Serializable {
    private PatientCommercialInsurance patientCommercialInsurance;
    private PatientInsuranceCompensationNoFault patientInsuranceCompensationNoFault;


}
