package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PatientDoctorSource;
import com.cob.salesforce.models.RecommendationDoctorDTO;

public class RecommendationDoctorDTOMapper {

    public static RecommendationDoctorDTO map(PatientDoctorSource entity) {
        RecommendationDoctorDTO dto = new RecommendationDoctorDTO();
        dto.setName(entity.getName());
        dto.setNpi(entity.getNpi());
        dto.setFax(entity.getFax().toString());
        dto.setDoctorAddress(AddressInfoDTOMapper.map(entity.getAddress()));
        return dto;
    }
}
