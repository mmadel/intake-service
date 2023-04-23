package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PatientMedicalHistory;
import com.cob.salesforce.models.MedicalHistoryInformationDTO;

import java.util.ArrayList;
import java.util.Arrays;

public class MedicalHistoryInformationDTOMapper {

    public static MedicalHistoryInformationDTO map(PatientMedicalHistory entity) {
        MedicalHistoryInformationDTO dto = new MedicalHistoryInformationDTO();
        dto.setHeight(entity.getHeight());
        dto.setWeight(entity.getWeight());
        dto.setEvaluationSubmission(entity.getMedicationPrescription());
        dto.setMedicationPrescription(entity.getMedicationPrescription());
        dto.setScanningTest(entity.getScanningTest());
        dto.setScanningTestValue(entity.getScanningTestValue());
        dto.setPacemaker(entity.getPacemaker());
        dto.setMetalImplantation(entity.getMetalImplantation());
        dto.setSurgeriesList(entity.getSurgeriesList());
        if(entity.getPatientCondition() != null)
            dto.setPatientCondition(new ArrayList<>(Arrays.asList(entity.getPatientCondition().split(","))));
        else
            dto.setPatientCondition(new ArrayList<>());
        return dto;
    }
}
