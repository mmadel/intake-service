package com.cob.salesforce.services.admin.clinic;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClinicCreatorServiceImpl implements ClinicCreatorService {
    @Autowired
    ClinicRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public ClinicModel create(ClinicModel model) {
        ClinicEntity createdEntity = repository.save(mapper.map(model, ClinicEntity.class));
        model.setId(createdEntity.getId());
        return model;
    }
}
