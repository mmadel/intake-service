package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientDoctorSource;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.mappers.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientDoctorSourceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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