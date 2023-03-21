package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientMedical;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientMedicalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientMedicalCreator implements IPatientDependencyCreator {
    @Autowired
    ModelMapper mapper;
    @Autowired
    PatientMedicalRepository repository;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PatientMedical toBeSaved = mapper.map(dto, PatientMedical.class);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
    }

    @PostConstruct
    public void init() {
        this.mapper.createTypeMap(PatientDTO.class, PatientMedical.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getAppointmentBooking(), PatientMedical::setAppointmentBooking);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getFamilyResultSubmission(), PatientMedical::setFamilyResultSubmission);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getPrimaryDoctor(), PatientMedical::setPrimaryDoctor);

                });
    }
}
