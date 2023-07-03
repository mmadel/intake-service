package com.cob.salesforce.mappers.entities;

import com.cob.salesforce.entity.PatientDependencyEntity;
import com.cob.salesforce.entity.PatientDoctorSource;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("PatientDoctorSourceMapper")
public class PatientDoctorSourceMapper implements PatientDependencyMapper {
    @Autowired
    ModelMapper mapper;

    @Override
    public PatientDependencyEntity map(PatientDTO dto) {
        return mapper.map(dto, PatientDoctorSource.class);
    }

    @PostConstruct
    public void init() {
        this.mapper.createTypeMap(PatientDTO.class, PatientDoctorSource.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationDoctor().getName(), PatientDoctorSource::setName);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationDoctor().getNpi(), PatientDoctorSource::setNpi);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationDoctor().getDoctorAddress().getFullAddress(), PatientDoctorSource::setAddress);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationDoctor().getFax(), PatientDoctorSource::setFax);
                });
    }
}
