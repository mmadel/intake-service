package com.cob.salesforce.mappers.entities;

import com.cob.salesforce.entity.PatientDependencyEntity;
import com.cob.salesforce.entity.PhysicalTherapy;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("PatientPhysicalTherapyMapper")
public class PatientPhysicalTherapyMapper implements PatientDependencyMapper {
    @Autowired
    ModelMapper mapper;

    @Override
    public PatientDependencyEntity map(PatientDTO dto) {
        return mapper.map(dto, PhysicalTherapy.class);
    }

    @PostConstruct
    public void init() {
        this.mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.createTypeMap(PatientDTO.class, PhysicalTherapy.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getPhysicalTherapy().getLocation(), PhysicalTherapy::setLocation);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getPhysicalTherapy().getNumberOfVisit(), PhysicalTherapy::setNumberOfVisit);
                });
    }
}
