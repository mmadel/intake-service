package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientSignatureDTO {
    private String signature;
    private byte[] signatureAsBytes;
    private Long patientId;
}
