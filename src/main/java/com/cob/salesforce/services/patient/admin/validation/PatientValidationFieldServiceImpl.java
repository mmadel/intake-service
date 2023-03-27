package com.cob.salesforce.services.patient.admin.validation;

import com.cob.salesforce.entity.validation.PatientFieldsEntity;
import com.cob.salesforce.models.validation.BasicInfo;
import com.cob.salesforce.models.validation.PatientFields;
import com.cob.salesforce.repositories.patient.admin.PatientValidationFieldRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.stream.StreamSupport;

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
        Iterable<PatientFieldsEntity> iterator= repository.findAll();
        if(StreamSupport.stream(iterator.spliterator(), false).count() > 0)
            return mapper.map(iterator.iterator().next(), PatientFields.class);
        else
            return null;
    }

    @Override
    public BasicInfo getPateintBasicInfo() {
        PatientFields result = null;
        result = mapper.map(repository.findAll().iterator().next(), PatientFields.class);
        return result.getBasicInfo();
    }
}
