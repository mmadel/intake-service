package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PhysicalTherapy;
import com.cob.salesforce.mappers.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.PatientPhysicalTherapyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PatientPhysicalTherapyCreator implements IPatientDependencyCreator {

    @Autowired
    PatientPhysicalTherapyRepository repository;
    @Autowired
    @Qualifier("PatientPhysicalTherapyMapper")
    PatientDependencyMapper mapper;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PhysicalTherapy toBeSaved = (PhysicalTherapy) mapper.map(dto);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setPhysicalTherapy(true);
    }



}
