package com.cob.salesforce.models.intake.signature;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientSignature {
    private byte[] signature;
    private Long patientId;
}
