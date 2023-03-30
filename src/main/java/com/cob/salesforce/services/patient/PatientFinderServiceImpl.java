package com.cob.salesforce.services.patient;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.mappers.PatientContainerMapper;
import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.models.PatientListData;
import com.cob.salesforce.repositories.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientFinderServiceImpl implements PatientFinderService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    @Qualifier("PatientContainerMapper")
    PatientContainerMapper mapper;

    @Override
    public PatientListData list(Pageable pageable) {
        Page<Patient> pages = patientRepository.findAll(pageable);
        long total = ((PageImpl) pages).getTotalElements();
        List<PatientContainerDTO> records = pages.stream().map(patient -> {
                    return mapper.map( patient);
                })
                .collect(Collectors.toList());

        return PatientListData.builder()
                .number_of_records(records.size())
                .number_of_matching_records((int) total)
                .records(records)
                .build();
    }
}
