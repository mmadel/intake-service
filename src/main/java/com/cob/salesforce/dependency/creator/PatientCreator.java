package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.mappers.entities.PatientMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.PatientRepository;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Getter
public class PatientCreator {
    @Autowired
    @Qualifier("PatientMapper")
    PatientMapper mapper;
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ClinicRepository clinicRepository;

    public Patient create(PatientDTO model)
    {
        Patient toBeSaved = mapper.map(model);
        Gson gson = new Gson();
        toBeSaved.setAgreement(gson.toJson(model.getAgreements()));
        toBeSaved.setClinic(clinicRepository.findById(model.getClinicId()).get());
        return patientRepository.save(toBeSaved);
    }

    public void update(Patient saved) {
        patientRepository.save(saved);
    }

}
