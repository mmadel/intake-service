package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.user.UserEntity;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserModel create(UserModel model) {
        UserEntity toBeSaved = mapper.map(model, UserEntity.class);
        toBeSaved.setClinics(model.getClinics().stream().map(clinicModel -> clinicRepository.findById(clinicModel.getId()).get()).collect(Collectors.toList()));
        //https://www.dariawan.com/tutorials/spring/illegalargumentexception-there-no-passwordencoder-mapped-id-null/
        toBeSaved.setPassword("{bcrypt}" + encoder.encode(model.getPassword()));
        UserEntity createdUser = repository.save(toBeSaved);
        model.setCreatedAt(createdUser.getCreatedAt());
        model.setId(createdUser.getId());
        return model;
    }
}
