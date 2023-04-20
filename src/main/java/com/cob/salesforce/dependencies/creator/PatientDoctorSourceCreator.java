package com.cob.salesforce.dependencies.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientDoctorSource;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.mappers.entities.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.PatientDoctorSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PatientDoctorSourceCreator implements IPatientDependencyCreator {
    @Autowired
    PatientDoctorSourceRepository repository;
    @Autowired
    @Qualifier("PatientDoctorSourceMapper")
    PatientDependencyMapper mapper;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PatientDoctorSource toBeSaved = (PatientDoctorSource) mapper.map(dto);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setPatientSourceType(PatientSourceType.Doctor);
    }


}
