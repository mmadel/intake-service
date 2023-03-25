package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.mappers.PatientDependencyMapper;
import com.cob.salesforce.mappers.PatientMapper;
import com.cob.salesforce.models.AddressInfoDTO;
import com.cob.salesforce.models.BasicInfoDTO;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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


    public Patient create(PatientDTO model) {
        Patient toBeSaved = mapper.map(model);
        return patientRepository.save(toBeSaved);
    }

    public void update(Patient saved) {
        patientRepository.save(saved);
    }

}
