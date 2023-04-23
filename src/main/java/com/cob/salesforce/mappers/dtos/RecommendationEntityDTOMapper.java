package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.models.RecommendationEntityDTO;

public class RecommendationEntityDTOMapper {

    public static RecommendationEntityDTO map(PatientEntitySource entity) {
        RecommendationEntityDTO dto = new RecommendationEntityDTO();
        dto.setName(entity.getName());
        return dto;
    }
}
