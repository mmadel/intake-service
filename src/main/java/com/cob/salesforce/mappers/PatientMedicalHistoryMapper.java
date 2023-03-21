package com.cob.salesforce.mappers;

import com.cob.salesforce.entity.PatientDependencyEntity;
import com.cob.salesforce.entity.PatientMedicalHistory;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("PatientMedicalHistoryMapper")
public class PatientMedicalHistoryMapper implements PatientDependencyMapper {
    @Autowired
    ModelMapper mapper;

    @Override
    public PatientDependencyEntity map(PatientDTO dto) {
        return mapper.map(dto, PatientMedicalHistory.class);
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(PatientDTO.class, PatientMedicalHistory.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalHistoryInformation().getHeight(), PatientMedicalHistory::setHeight);
                    mapper.map(src -> src.getMedicalHistoryInformation().getWeight(), PatientMedicalHistory::setWeight);
                    mapper.map(src -> src.getMedicalHistoryInformation().getEvaluationSubmission(), PatientMedicalHistory::setEvaluationSubmission);
                    mapper.map(src -> src.getMedicalHistoryInformation().getMedicationPrescription(), PatientMedicalHistory::setMedicationPrescription);
                    mapper.map(src -> src.getMedicalHistoryInformation().getScanningTest(), PatientMedicalHistory::setScanningTest);
                    mapper.map(src -> src.getMedicalHistoryInformation().getScanningTestValue(), PatientMedicalHistory::setScanningTestValue);
                    mapper.map(src -> src.getMedicalHistoryInformation().getPatientCondition(), PatientMedicalHistory::setPatientCondition);
                    mapper.map(src -> src.getMedicalHistoryInformation().getMetalImplantation(), PatientMedicalHistory::setMetalImplantation);
                    mapper.map(src -> src.getMedicalHistoryInformation().getPacemaker(), PatientMedicalHistory::setPacemaker);
                    mapper.map(src -> src.getMedicalHistoryInformation().getSurgeriesList(), PatientMedicalHistory::setSurgeriesList);
                });
    }
}
