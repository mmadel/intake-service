package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.mappers.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientEntitySourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PatientEntitySourceCreator implements IPatientDependencyCreator {
    @Autowired
    PatientEntitySourceRepository repository;
    @Autowired
    @Qualifier("PatientEntitySourceMapper")
    PatientDependencyMapper mapper;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PatientEntitySource toBeSaved = (PatientEntitySource) mapper.map(dto);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setPatientSourceType(PatientSourceType.Entity);
    }
}
