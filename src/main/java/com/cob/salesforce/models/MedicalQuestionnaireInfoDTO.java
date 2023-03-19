
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicalQuestionnaireInfoDTO {

    
    private String appointmentBooking;
    
    private Boolean familyResultSubmission;
    
    private PhysicalTherapyDTO physicalTherapyDTO;
    
    private Boolean physicalTherapyReceiving;
    
    private String primaryDoctor;
    
    private RecommendationDoctorDTO recommendationDoctorDTO;
    
    private RecommendationEntityDTO recommendationEntityDTO;

}
