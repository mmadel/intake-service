package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientMedicalHistory;
import com.cob.salesforce.mappers.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientMedicalHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientMedicalHistoryCreator implements IPatientDependencyCreator {
    @Autowired
    @Qualifier("PatientMedicalHistoryMapper")
    PatientDependencyMapper mapper;
    @Autowired
    PatientMedicalHistoryRepository repository;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PatientMedicalHistory toBeSaved = (PatientMedicalHistory) mapper.map(dto);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
    }


}
