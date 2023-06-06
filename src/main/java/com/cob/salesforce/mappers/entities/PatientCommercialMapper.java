package com.cob.salesforce.mappers.entities;

import com.cob.salesforce.entity.*;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("PatientCommercialMapper")
public class PatientCommercialMapper implements PatientDependencyMapper {
    @Autowired
    ModelMapper mapper;

    @Override
    public PatientDependencyEntity map(PatientDTO dto) {
        InsuranceWorkerCommercial toBeSaved = mapper.map(dto, InsuranceWorkerCommercial.class);
        if (dto.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getIsSecondaryInsurance() != null)
            toBeSaved.setSecondaryInsurance(mapper.map(dto, SecondaryInsurance.class));
        if (dto.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getIsMedicareCoverage() != null)
            toBeSaved.setMedicareCoverage(mapper.map(dto, MedicareCoverage.class));
        switch (toBeSaved.getRelationship()) {
            case Spouse:
            case Child:
            case Other:
                toBeSaved.setPatientRelationship(mapper.map(dto, PatientRelationship.class));
                break;
        }
        return toBeSaved;
    }

    @PostConstruct
    public void init() {
        this.mapper.getConfiguration().setAmbiguityIgnored(true);
        this.mapper.createTypeMap(PatientDTO.class, InsuranceWorkerCommercial.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getInsuranceCompanyId(), InsuranceWorkerCommercial::setInsuranceCompanyId);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getMemberId(), InsuranceWorkerCommercial::setMemberId);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getPolicyId(), InsuranceWorkerCommercial::setPolicyId);
                    mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getRelationshipEnum(), InsuranceWorkerCommercial::setRelationship);
                    mapper.skip(InsuranceWorkerCommercial::setSecondaryInsurance);
                    mapper.skip(InsuranceWorkerCommercial::setMedicareCoverage);
                    mapper.skip(InsuranceWorkerCommercial::setPatientRelationship);
                });
        this.mapper.createTypeMap(PatientDTO.class, SecondaryInsurance.class).addMappings(mapper -> {
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getSecondaryInsuranceDTO().getInsuranceCompanyName(), SecondaryInsurance::setInsuranceCompanyName);
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getSecondaryInsuranceDTO().getFullName(), SecondaryInsurance::setPolicyHolderName);
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getSecondaryInsuranceDTO().getMemberId(), SecondaryInsurance::setMemberId);
        });
        this.mapper.createTypeMap(PatientDTO.class, MedicareCoverage.class).addMappings(mapper -> {
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getMedicareCoverageDTO().getFullName(), MedicareCoverage::setEmployerName);
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getMedicareCoverageDTO().getEmployerPhone(), MedicareCoverage::setEmployerPhone);
        });

        this.mapper.createTypeMap(PatientDTO.class, PatientRelationship.class).addMappings(mapper -> {
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getPatientRelationshipDTO().getFullName(), PatientRelationship::setPatientRelationshipName);
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getPatientRelationshipDTO().getPatientRelationshipPhone(), PatientRelationship::setPatientRelationshipPhone);
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getPatientRelationshipDTO().getEmployerName(), PatientRelationship::setEmployerName);
        });
    }
}
