package com.cob.salesforce.services.admin.user;

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
                .map(userEntity -> {
                    return mapper.map(userEntity, UserModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserModel getById(Long Id) {
        return mapper.map(repository.findById(Id).get(), UserModel.class);
    }
}
