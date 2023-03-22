
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicalQuestionnaireInfoDTO {

    
    private String appointmentBooking;
    
    private Boolean familyResultSubmission;
    
    private PhysicalTherapyDTO physicalTherapy;
    
    private Boolean physicalTherapyReceiving;

    private Boolean isDoctorRecommended;
    
    private String primaryDoctor;
    
    private RecommendationDoctorDTO recommendationDoctor;
    
    private RecommendationEntityDTO recommendationEntity;

}
