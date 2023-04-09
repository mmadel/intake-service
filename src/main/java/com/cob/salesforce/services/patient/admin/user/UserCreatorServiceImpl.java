package com.cob.salesforce.services.patient.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.user.UserEntity;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.patient.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.patient.admin.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserCreatorServiceImpl implements UserCreatorService {
    @Autowired
    UserRepository repository;

    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public UserModel create(UserModel model) {
        UserEntity toBeSaved = mapper.map(model, UserEntity.class);
        toBeSaved.setClinics(model.getClinics().stream().map(clinicModel -> {
            return clinicRepository.findById(clinicModel.getId()).get();
        }).collect(Collectors.toList()));
        UserEntity createdUser = repository.save(toBeSaved);
        model.setCreatedAt(createdUser.getCreatedAt());
        model.setId(createdUser.getId());
        return model;
    }
}
