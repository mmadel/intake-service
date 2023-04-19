package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PhysicalTherapy;
import com.cob.salesforce.models.PhysicalTherapyDTO;

public class PhysicalTherapyDTOMapper {

    public PhysicalTherapyDTO map(PhysicalTherapy entity) {
        return PhysicalTherapyDTO.builder()
                .location(entity.getLocation())
                .numberOfVisit(entity.getNumberOfVisit().longValue())
                .build();
    }
}
