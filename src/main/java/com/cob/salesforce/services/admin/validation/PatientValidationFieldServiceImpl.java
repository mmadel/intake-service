package com.cob.salesforce.services.admin.validation;

import com.cob.salesforce.entity.validation.PatientFieldEntity;
import com.cob.salesforce.models.intake.fields.PatientField;
import com.cob.salesforce.repositories.PatientFieldsRepository;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PatientValidationFieldServiceImpl implements PatientValidationFieldService {

    @Autowired
    PatientFieldsRepository patientFieldsRepository;

    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public PatientField change(PatientField patientFields) {
        Optional<PatientFieldEntity> patientFieldEntity = patientFieldsRepository.findByClinicId(patientFields.getClinicId());
        PatientFieldEntity fieldEntity = mapper.map(patientFields, PatientFieldEntity.class);
        if (!patientFieldEntity.isEmpty()) {
            fieldEntity.setId(patientFieldEntity.get().getId());
        }
        patientFieldsRepository.save(fieldEntity);
        return patientFields;
    }

    @Override
    public PatientField get(Long clinicId) {
        PatientFieldEntity fieldEntity = patientFieldsRepository.findByClinicId(clinicId).get();
        return mapper.map(fieldEntity, PatientField.class);
    }

}
