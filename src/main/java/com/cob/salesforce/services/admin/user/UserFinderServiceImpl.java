package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.UserClinicEntity;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserFinderServiceImpl implements UserFinderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClinicRepository clinicRepository;
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
    public List<ClinicModel> findByUserId(String userId) {
        List<Long> clinicIds = new ArrayList<>();
        userRepository.findByUserId(userId).get()
                .forEach(userClinicEntity -> {
                    clinicIds.add(userClinicEntity.getClinicId());
                });
        List<ClinicModel> clinicModels = new ArrayList<>();
        clinicRepository.findAllById(clinicIds)
                .forEach(clinicEntity -> {
                    clinicModels.add(mapper.map(clinicEntity, ClinicModel.class));
                });
        return clinicModels;
    }


}
