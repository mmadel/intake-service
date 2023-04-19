package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.models.BasicInfoDTO;


public class PatientBasicInfoDTOMapper {
    public static BasicInfoDTO map(Patient entity) {
        String fName = entity.getName().split(",")[0];
        String mName = entity.getName().split(",")[1];
        String lName = entity.getName().split(",")[2];
        return BasicInfoDTO.builder()
                .firstName(fName)
                .middleName(mName)
                .lastName(lName)
                .birthDate(entity.getDateOfBirth())
                .email(entity.getEmail())
                .emergencyName(entity.getEmergencyName())
                .emergencyPhone(entity.getEmergencyPhone())
                .employmentStatus(entity.getEmploymentStatus().label)
                .gender(entity.getGender().label)
                .idType(entity.getIdType().label)
                .patientId(entity.getPatientId())
                .phoneType(entity.getPhoneType().label)
                .phoneNumber(entity.getPhone())
                .idEffectiveTo(entity.getIdEffectiveTo())
                .idEffectiveFrom(entity.getIdEffectiveFrom())
                .maritalStatus(entity.getMaritalStatus().label)
                .build();
    }
}
