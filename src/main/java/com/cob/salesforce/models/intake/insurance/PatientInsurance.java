package com.cob.salesforce.models.intake.insurance;

import com.cob.salesforce.models.intake.insurance.commercial.PatientCommercialInsurance;
import com.cob.salesforce.models.intake.insurance.compensation.PatientInsuranceCompensationNoFault;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientInsurance implements Serializable {
    private PatientCommercialInsurance patientCommercialInsurance;
    private PatientInsuranceCompensationNoFault patientInsuranceCompensationNoFault;


}
