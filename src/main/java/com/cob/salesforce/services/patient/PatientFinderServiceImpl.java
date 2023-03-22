package com.cob.salesforce.services.patient;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.mappers.PatientMapper;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientFinderServiceImpl implements PatientFinderService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    @Qualifier("PatientMapper")
    PatientMapper mapper;

    @Override
    public List<PatientDTO> list() {
        return StreamSupport.stream(patientRepository.findAll().spliterator(), false)
                .map(patient -> {
                    return mapper.map(patient);
                })
                .collect(Collectors.toList());
    }
}
