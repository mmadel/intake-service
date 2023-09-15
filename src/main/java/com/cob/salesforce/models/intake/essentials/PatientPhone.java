package com.cob.salesforce.models.intake.essentials;

import com.cob.salesforce.enums.PhoneType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientPhone implements Serializable {
    private PhoneType phoneType;
    private String phone;
}
