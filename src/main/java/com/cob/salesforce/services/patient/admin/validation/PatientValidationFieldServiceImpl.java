package com.cob.salesforce.services.patient.admin.validation;

import com.cob.salesforce.entity.validation.PatientFieldsEntity;
import com.cob.salesforce.models.validation.PatientFields;
import com.cob.salesforce.repositories.patient.admin.PatientValidationFieldRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PatientValidationFieldServiceImpl implements PatientValidationFieldService {
    @Autowired
    PatientValidationFieldRepository repository;
    @Autowired
    ModelMapper mapper;

    public PatientFields change(PatientFields patientFields) {
        repository.save(mapper.map(patientFields, PatientFieldsEntity.class));
        return patientFields;
    }

    @Override
    public PatientFields get() {
        PatientFields result = null;
            result = mapper.map(repository.findAll().iterator().next(), PatientFields.class);
        return result;
    }
}
