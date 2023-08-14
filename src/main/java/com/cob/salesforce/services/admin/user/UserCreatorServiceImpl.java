package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.user.UserEntity;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

}
