package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PatientMedicalHistory;
import com.cob.salesforce.models.MedicalHistoryInformationDTO;

import java.util.ArrayList;
import java.util.Arrays;

public class MedicalHistoryInformationDTOMapper {

    public  static MedicalHistoryInformationDTO map(PatientMedicalHistory entity) {
        return MedicalHistoryInformationDTO.builder()
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .evaluationSubmission(entity.getMedicationPrescription())
                .medicationPrescription(entity.getMedicationPrescription())
                .scanningTest(entity.getScanningTest())
                .scanningTestValue(entity.getScanningTestValue())
                .pacemaker(entity.getPacemaker())
                .metalImplantation(entity.getMetalImplantation())
                .surgeriesList(entity.getSurgeriesList())
                .patientCondition(new ArrayList<>(Arrays.asList(entity.getPatientCondition().split(","))))
                .build();
    }
}
