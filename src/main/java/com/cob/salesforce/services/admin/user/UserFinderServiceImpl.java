package com.cob.salesforce.services.admin.user;

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
        return null;
    }

    @Override
    public UserModel getById(Long Id) {
        return null;
    }


    @Override
    public List<ClinicModel> findByUserId(Long userId) {
//        return repository.findUserClinics(userId).stream()
//                .map(clinicEntity -> mapper.map(clinicEntity, ClinicModel.class))
//                .collect(Collectors.toList());
        return null;
    }


}
