package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.user.UserEntity;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserFinderServiceImpl implements UserFinderService {
    @Autowired
    UserRepository repository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<UserModel> getAll() {
        return StreamSupport
                .stream(Spliterators
                        .spliteratorUnknownSize(repository.findAll().iterator(), 0), false)
                .map(entity -> mapUserModel(entity))
                .collect(Collectors.toList());
    }

    @Override
    public UserModel getById(Long Id) {
        UserEntity entity = repository.findById(Id).get();
        return mapUserModel(entity);
    }

    @Override
    public List<ClinicModel> findByUserId(Long userId) {
        return repository.findUserClinics(userId).stream()
                .map(clinicEntity -> mapper.map(clinicEntity, ClinicModel.class))
                .collect(Collectors.toList());
    }

    private UserModel mapUserModel(UserEntity entity){
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setPassword(entity.getPassword());
        model.setAddress(entity.getAddress());
        model.setUserRole(entity.getUserRole());
        model.setClinics(entity.getClinics().stream().map(clinicEntity -> mapper.map(clinicEntity, ClinicModel.class)).collect(Collectors.toList()));
        return model;
    }
}
