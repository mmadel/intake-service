package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.*;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.mappers.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.InsuranceWorkerCommercialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PatientCommercialCreator implements IPatientDependencyCreator {
    @Autowired
    InsuranceWorkerCommercialRepository repository;
    @Autowired
    @Qualifier("PatientCommercialMapper")
    PatientDependencyMapper patientCommercialMapper;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        InsuranceWorkerCommercial toBeSaved = (InsuranceWorkerCommercial) patientCommercialMapper.map(dto);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setInsuranceWorkerType(InsuranceWorkerType.Commercial);
    }


}
