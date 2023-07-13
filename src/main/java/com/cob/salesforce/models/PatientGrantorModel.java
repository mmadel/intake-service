package com.cob.salesforce.models;

import com.cob.salesforce.enums.EmergencyRelation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class PatientGrantorModel {
    private Long id;

    private String firstName;


    private String middleName;


    private String lastName;


    private EmergencyRelation relation;

    private byte[] idFront;
    private byte[] idBack;
}
