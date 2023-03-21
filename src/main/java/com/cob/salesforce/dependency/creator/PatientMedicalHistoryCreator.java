package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientMedicalHistory;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientMedicalHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientMedicalHistoryCreator implements IPatientDependencyCreator {
    @Autowired
    ModelMapper mapper;
    @Autowired
    PatientMedicalHistoryRepository repository;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PatientMedicalHistory toBeSaved = mapper.map(dto, PatientMedicalHistory.class);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
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
