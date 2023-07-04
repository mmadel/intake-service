package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.user.UserEntity;
import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        Set<ClinicEntity> clinics = null;
        if(model.getClinics() != null)
            clinics = model.getClinics().stream().map(clinicModel -> clinicRepository.findById(clinicModel.getId()).get()).collect(Collectors.toSet());
        toBeSaved.setClinics(clinics);
        //https://www.dariawan.com/tutorials/spring/illegalargumentexception-there-no-passwordencoder-mapped-id-null/
        toBeSaved.setPassword("{bcrypt}" + encoder.encode(model.getPassword()));
        UserEntity createdUser = repository.save(toBeSaved);
        model.setCreatedAt(createdUser.getCreatedAt());
        model.setId(createdUser.getId());
        return model;
    }
    @Override
    public UserModel update(UserModel model) throws UserException {

        Optional<UserEntity> entity = repository.getByName(model.getName());

        UserEntity toBeUpdate = mapUserToEntity(model);
        if (entity.isPresent() && !model.getName().equals(entity.get().getName()))
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_IS_EXISTS,
                    new Object[]{model.getName()});
        if (entity.isPresent() && entity.get().getPassword().equals(model.getPassword()))
            toBeUpdate.setPassword(model.getPassword());
        else
            toBeUpdate.setPassword("{bcrypt}" + encoder.encode(model.getPassword()));
        toBeUpdate.setCreatedAt(new Date().getTime());
        UserEntity updatedUser = repository.save(toBeUpdate);
        model.setCreatedAt(updatedUser.getCreatedAt());
        return model;
    }
    private UserEntity mapUserToEntity(UserModel model) {
        //https://www.dariawan.com/tutorials/spring/illegalargumentexception-there-no-passwordencoder-mapped-id-null/
        UserEntity entity = mapper.map(model, UserEntity.class);
        entity.setClinics(getClinics(model.getClinics()));
        return entity;
    }
    private Set<ClinicEntity> getClinics(List<ClinicModel> clinicsModels) {
        Set<ClinicEntity> clinics = null;
        if (clinicsModels != null)
            clinics = clinicsModels.stream().map(clinicModel -> clinicRepository.findById(clinicModel.getId()).get()).collect(Collectors.toSet());
        return clinics;
    }
    public void delete(long id) throws UserException {
        Optional<UserEntity> entity = repository.findById(id);
        if(entity.isEmpty())
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_NOT_FOUND,
                    new Object[]{id});
        UserEntity toBeDeleted = entity.get();
        toBeDeleted.setClinics(null);
        repository.delete(toBeDeleted);
    }
}
