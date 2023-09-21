package com.cob.salesforce.models.intake.grantor;

import com.cob.salesforce.enums.EmergencyRelation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
public class PatientGrantor {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private EmergencyRelation relation;

    private byte[] idFront;

    private byte[] idBack;
}
