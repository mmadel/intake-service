package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PatientMedical;
import com.cob.salesforce.models.MedicalQuestionnaireInfoDTO;

public class MedicalQuestionnaireInfoDTOMapper {

    public static MedicalQuestionnaireInfoDTO map(PatientMedical entity){
        return MedicalQuestionnaireInfoDTO.builder()
                .appointmentBooking(entity.getAppointmentBooking())
                .familyResultSubmission(entity.getFamilyResultSubmission())
                .physicalTherapyReceiving(entity.getFamilyResultSubmission())
                .primaryDoctor(entity.getPrimaryDoctor())
                .build();
    }
}
