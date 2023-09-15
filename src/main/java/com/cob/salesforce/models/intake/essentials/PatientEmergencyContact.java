package com.cob.salesforce.models.intake.essentials;

import com.cob.salesforce.enums.EmergencyRelation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientEmergencyContact  implements Serializable {

    private String emergencyName;

    private String emergencyPhone;
    private EmergencyRelation emergencyRelation;
}
