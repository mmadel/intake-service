package com.cob.salesforce.services.intake;

import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.models.intake.container.list.PatientRecord;

public class PatientListRecordMapper {

    public static PatientRecord map(PatientEntity patient) {
        return PatientRecord
                .builder()
                .firstName(patient.getPatientEssentialInformation().getPatientName().getFirstName())
                .middleName(patient.getPatientEssentialInformation().getPatientName().getMiddleName())
                .lastName(patient.getPatientEssentialInformation().getPatientName().getLastName())
                .email(patient.getPatientEssentialInformation().getEmail())
                .phoneNumber(patient.getPatientEssentialInformation().getPatientPhone().getPhone())
                .sourceType(PatientDependenciesMapper.mapToSourceType(patient.getId()))
                .insuranceType(PatientDependenciesMapper.mapToInsuranceType(patient.getId()))
                .hasGuarantor(PatientDependenciesMapper.mapToHasGuarantor(patient.getId()))
                .patientId(patient.getId())
                .build();
    }
}
