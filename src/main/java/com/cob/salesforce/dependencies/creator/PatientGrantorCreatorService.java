package com.cob.salesforce.dependencies.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientGrantor;
import com.cob.salesforce.models.PatientGrantorModel;
import com.cob.salesforce.repositories.PatientGrantorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientGrantorCreatorService {
    @Autowired
    PatientGrantorRepository patientGrantorRepository;

    @Autowired
    ModelMapper mapper;
    public void create(Patient savedPatient  , PatientGrantorModel model){
        PatientGrantor toBeSaved = mapper.map(model, PatientGrantor.class);
        toBeSaved.setPatient(savedPatient);
        patientGrantorRepository.save(toBeSaved);

    }
}
