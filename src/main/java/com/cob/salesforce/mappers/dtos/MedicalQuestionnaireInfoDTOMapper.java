package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PatientMedical;
import com.cob.salesforce.models.MedicalQuestionnaireInfoDTO;

public class MedicalQuestionnaireInfoDTOMapper {

    public static MedicalQuestionnaireInfoDTO map(PatientMedical entity) {
        MedicalQuestionnaireInfoDTO dto = new MedicalQuestionnaireInfoDTO();
        dto.setAppointmentBooking(entity.getAppointmentBooking());
        dto.setFamilyResultSubmission(entity.getFamilyResultSubmission());
        dto.setPhysicalTherapyReceiving(entity.getFamilyResultSubmission());
        dto.setPrimaryDoctor(entity.getPrimaryDoctor());
        return dto;
    }
}
