package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.InsuranceWorkerCompNoFault;
import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.InsuranceWorkerInsuranceWorkerCompNoFaultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientCompNoFaultCreator implements IPatientDependencyCreator {
    @Autowired
    ModelMapper mapper;
    @Autowired
    InsuranceWorkerInsuranceWorkerCompNoFaultRepository repository;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        InsuranceWorkerCompNoFault toBeSaved = mapper.map(dto, InsuranceWorkerCompNoFault.class);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setInsuranceWorkerType(InsuranceWorkerType.Comp_NoFault);
    }

    @PostConstruct
    public void init() {
        this.mapper.getConfiguration().setAmbiguityIgnored(true);
        this.mapper.createTypeMap(PatientDTO.class, InsuranceWorkerCompNoFault.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getInjuryType(), InsuranceWorkerCompNoFault::setInjuryType);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getAccidentDate(), InsuranceWorkerCompNoFault::setAccidentDate);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getWorkerStatusEnum(), InsuranceWorkerCompNoFault::setWorkerStatus);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getAddress(), InsuranceWorkerCompNoFault::setAddress);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getFax(), InsuranceWorkerCompNoFault::setFax);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getInsuranceName(), InsuranceWorkerCompNoFault::setInsuranceName);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getClaimNumber(), InsuranceWorkerCompNoFault::setClaimNumber);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getAdjusterName(), InsuranceWorkerCompNoFault::setAdjusterName);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getAdjusterPhone(), InsuranceWorkerCompNoFault::setAdjusterPhone);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getAttorneyName(), InsuranceWorkerCompNoFault::setAttorneyName);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getAttorneyPhone(), InsuranceWorkerCompNoFault::setAttorneyPhone);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCompNoFault().getCaseStatusEnum(), InsuranceWorkerCompNoFault::setCaseStatus);
                });
    }
}
