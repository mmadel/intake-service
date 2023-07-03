package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PhysicalTherapy;
import com.cob.salesforce.models.PhysicalTherapyDTO;

public class PhysicalTherapyDTOMapper {

    public static PhysicalTherapyDTO map(PhysicalTherapy entity) {
        PhysicalTherapyDTO dto = new PhysicalTherapyDTO();
        dto.setLocation(entity.getLocation());
        dto.setNumberOfVisit(entity.getNumberOfVisit().longValue());
        return dto;
    }
}
