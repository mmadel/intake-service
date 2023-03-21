package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientDoctorSource;
import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientEntitySourceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientEntitySourceCreator implements IPatientDependencyCreator{
    @Autowired
    PatientEntitySourceRepository repository;
    @Autowired
    ModelMapper mapper;
    @Override
    public void create(PatientDTO dto, Patient entity) {
        PatientEntitySource toBeSaved = mapper.map(dto, PatientEntitySource.class);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setPatientSourceType(PatientSourceType.Entity);
    }

    @PostConstruct
    public void init() {
        this.mapper.createTypeMap(PatientDTO.class, PatientEntitySource.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationEntity().getName(), PatientEntitySource::setName);
                });
    }
}
