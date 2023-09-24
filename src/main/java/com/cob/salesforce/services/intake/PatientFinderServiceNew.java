package com.cob.salesforce.services.intake;


import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.container.list.PatientListContainer;
import com.cob.salesforce.models.intake.container.list.PatientRecord;
import com.cob.salesforce.models.intake.grantor.PatientGrantor;
import com.cob.salesforce.models.intake.insurance.PatientInsurance;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.cob.salesforce.repositories.intake.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientFinderServiceNew {
    @Autowired
    PatientRepositoryNew patientRepository;
    @Autowired
    ModelMapper mapper;

    public PatientListContainer getPatients(Pageable pageable, Long clinicId) {
        Page<PatientEntity> pages = patientRepository.findByClinicId(pageable, clinicId);
        List<PatientEntity> entities = pages.getContent();
        long total = (pages).getTotalElements();
        List<PatientRecord> records = constructPatientRecords(entities);
        return getPatientListContainer(total, records);
    }

    public Patient getPatient(Long id) {
        PatientEntity patientEntity = patientRepository.findById(id).get();
        Patient patient = mapper.map(patientEntity, Patient.class);
        PatientDependenciesMapper.mapper(patient);
        return patient;
    }

    private PatientListContainer getPatientListContainer(long total, List<PatientRecord> records) {
        return PatientListContainer.builder()
                .records(records)
                .number_of_matching_records((int) total)
                .records(records)
                .build();
    }

    private List<PatientRecord> constructPatientRecords(List<PatientEntity> entities) {
        return entities.stream()
                .map(patient -> PatientListRecordMapper.map(patient)).collect(Collectors.toList());
    }

}