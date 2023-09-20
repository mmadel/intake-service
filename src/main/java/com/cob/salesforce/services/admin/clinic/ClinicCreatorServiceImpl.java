package com.cob.salesforce.services.admin.clinic;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.UserClinicEntity;
import com.cob.salesforce.exception.business.ClinicException;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClinicCreatorServiceImpl implements ClinicCreatorService {
    @Autowired
    ClinicRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public ClinicModel create(ClinicModel model) {
        ClinicEntity createdEntity = repository.save(mapper.map(model, ClinicEntity.class));
        model.setId(createdEntity.getId());
        return model;
    }
}
