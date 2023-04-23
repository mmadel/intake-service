package com.cob.salesforce.dependencies.creator;

import com.cob.salesforce.entity.InsuranceWorkerCompNoFault;
import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.mappers.entities.PatientDependencyMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.InsuranceWorkerInsuranceWorkerCompNoFaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PatientCompNoFaultCreator implements IPatientDependencyCreator {
    @Autowired
    @Qualifier("PatientCompNoFaultMapper")
    PatientDependencyMapper mapper;
    @Autowired
    InsuranceWorkerInsuranceWorkerCompNoFaultRepository repository;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        InsuranceWorkerCompNoFault toBeSaved = (InsuranceWorkerCompNoFault) mapper.map(dto);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setInsuranceWorkerType(InsuranceWorkerType.Comp_NoFault);
    }
}
