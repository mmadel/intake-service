package com.cob.salesforce.mappers;

import com.cob.salesforce.entity.InsuranceWorkerCompNoFault;
import com.cob.salesforce.entity.PatientDependencyEntity;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("PatientCompNoFaultMapper")
public class PatientCompNoFaultMapper implements PatientDependencyMapper {
    @Autowired
    ModelMapper mapper;

    @Override
    public PatientDependencyEntity map(PatientDTO model) {
        return mapper.map(model, InsuranceWorkerCompNoFault.class);
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
