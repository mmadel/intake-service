package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.*;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.Relationship;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.InsuranceWorkerCommercialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientCommercialCreator implements IPatientDependencyCreator {
    @Autowired
    InsuranceWorkerCommercialRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        InsuranceWorkerCommercial toBeSaved = mapper.map(dto, InsuranceWorkerCommercial.class);
        if (dto.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getIsSecondaryInsurance())
            toBeSaved.setSecondaryInsurance(mapper.map(dto, SecondaryInsurance.class));
        if (dto.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getIsMedicareCoverage())
            toBeSaved.setMedicareCoverage(mapper.map(dto, MedicareCoverage.class));
        switch (toBeSaved.getRelationship()) {
            case Spouse:
            case Child:
            case Other:
                toBeSaved.setPatientRelationship(mapper.map(dto, PatientRelationship.class));
                break;
        }
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setInsuranceWorkerType(InsuranceWorkerType.Commercial);
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
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getSecondaryInsuranceDTO().getPolicyHolderName(), SecondaryInsurance::setPolicyHolderName);
        });
        this.mapper.createTypeMap(PatientDTO.class, MedicareCoverage.class).addMappings(mapper -> {
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getMedicareCoverageDTO().getEmployerName(), MedicareCoverage::setEmployerName);
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getMedicareCoverageDTO().getEmployerPhone(), MedicareCoverage::setEmployerPhone);
        });

        this.mapper.createTypeMap(PatientDTO.class, PatientRelationship.class).addMappings(mapper -> {
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getPatientRelationshipDTO().getPatientRelationshipName(), PatientRelationship::setPatientRelationshipName);
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getPatientRelationshipDTO().getPatientRelationshipPhone(), PatientRelationship::setPatientRelationshipPhone);
            mapper.map(src -> src.getInsuranceQuestionnaireInfo().getInsuranceWorkerCommercial().getPatientRelationshipDTO().getEmployerName(), PatientRelationship::setEmployerName);
        });
    }
}
