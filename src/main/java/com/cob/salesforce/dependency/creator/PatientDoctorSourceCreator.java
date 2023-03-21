package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientDoctorSource;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientDoctorSourceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientDoctorSourceCreator implements IPatientDependencyCreator {
    @Autowired
    PatientDoctorSourceRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PatientDoctorSource toBeSaved = mapper.map(dto, PatientDoctorSource.class);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setPatientSourceType(PatientSourceType.Doctor);
    }

    @PostConstruct
    public void init() {
        this.mapper.createTypeMap(PatientDTO.class, PatientDoctorSource.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationDoctor().getName(), PatientDoctorSource::setName);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationDoctor().getNpi(), PatientDoctorSource::setNpi);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationDoctor().getAddress(), PatientDoctorSource::setAddress);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getRecommendationDoctor().getFax(), PatientDoctorSource::setFax);
                });
    }
}
