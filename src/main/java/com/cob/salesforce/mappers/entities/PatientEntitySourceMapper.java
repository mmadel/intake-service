package com.cob.salesforce.mappers.entities;

import com.cob.salesforce.entity.PatientDependencyEntity;
import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("PatientEntitySourceMapper")
public class PatientEntitySourceMapper implements PatientDependencyMapper {
    @Autowired
    ModelMapper mapper;

    @Override
    public PatientDependencyEntity map(PatientDTO dto) {
        return mapper.map(dto, PatientEntitySource.class);
    }

    @PostConstruct
    public void init() {
        this.mapper.createTypeMap(PatientDTO.class, PatientEntitySource.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationEntity().getName(), PatientEntitySource::setName);
                });
    }
}
