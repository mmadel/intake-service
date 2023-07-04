package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.models.BasicInfoDTO;


public class PatientBasicInfoDTOMapper {
    public static BasicInfoDTO map(Patient entity) {
        String fName = entity.getName().split(",")[0];
        String mName = entity.getName().split(",")[1];
        String lName = entity.getName().split(",")[2];
        BasicInfoDTO dto = new BasicInfoDTO();
        dto.setFirstName(fName);
        dto.setMiddleName(mName);
        dto.setLastName(lName);
        dto.setBirthDate(entity.getDateOfBirth());
        dto.setEmail(entity.getEmail());
        dto.setEmergencyName(entity.getEmergencyName());
        dto.setEmergencyPhone(entity.getEmergencyPhone());
        dto.setEmploymentStatus(entity.getEmploymentStatus().label);
        dto.setEmploymentCompany(entity.getEmploymentCompany());
        dto.setGender(entity.getGender().label);
//        dto.setIdType(entity.getIdType().label);
//        dto.setPatientId(entity.getPatientId());
        dto.setPhoneType(entity.getPhoneType().label);
        dto.setPhoneNumber(entity.getPhone());
//        dto.setIdEffectiveTo(entity.getIdEffectiveTo());
//        dto.setIdEffectiveFrom(entity.getIdEffectiveFrom());
        dto.setMaritalStatus(entity.getMaritalStatus().label);
        return dto;

    }
}
