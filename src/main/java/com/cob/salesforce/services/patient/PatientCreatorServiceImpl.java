package com.cob.salesforce.services.patient;

import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientCreatorServiceImpl implements PatientCreatorService {
    @Autowired
    PatientRepository patientRepository ;
    public PatientDTO create(PatientDTO model) {
        return null;
    }
}
