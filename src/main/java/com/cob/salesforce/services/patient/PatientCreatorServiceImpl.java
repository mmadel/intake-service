package com.cob.salesforce.services.patient;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.mappers.patient.PatientMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientCreatorServiceImpl implements PatientCreatorService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientMapper patientMapper;

    public PatientDTO create(PatientDTO model) {
        Patient toBeCreated = patientMapper.map(model);
        patientRepository.save(patientMapper.map(model));
        return model;
    }
}
