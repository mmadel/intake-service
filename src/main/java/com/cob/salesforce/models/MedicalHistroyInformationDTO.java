
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MedicalHistroyInformationDTO {


    private List<String> patientCondition;

    private String scannigValue;

}
