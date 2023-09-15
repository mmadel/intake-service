package com.cob.salesforce.models.intake.agreement;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientAgreement implements Serializable {
    private Long agreementId;
    private boolean isAccepted;
}
