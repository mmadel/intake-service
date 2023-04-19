package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientMedical;
import com.cob.salesforce.mappers.entities.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.PatientMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PatientMedicalCreator implements IPatientDependencyCreator {

    @Autowired
    PatientMedicalRepository repository;
    @Autowired
    @Qualifier("PatientMedicalMapper")
    PatientDependencyMapper mapper;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PatientMedical toBeSaved = (PatientMedical) mapper.map(dto);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
    }


}
