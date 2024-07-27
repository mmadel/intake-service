package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.UserEntity;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.services.security.keycloak.user.AssignUserRolesUseCase;
import com.cob.salesforce.services.security.keycloak.user.UnAssignUserRoleUseCase;
import com.cob.salesforce.services.security.keycloak.user.UpdateKeycloakUserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@Slf4j
public class UserUpdateService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ModelMapper mapper;

    @Autowired
    UpdateKeycloakUserService updateKeycloakUserService;

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void update(UserModel userModel) {
        UserEntity entity  = userRepository.findByUserId(userModel.getUuid()).get();
        UserEntity toBeUpdated = mapper.map(userModel, UserEntity.class);
        toBeUpdated.setId(entity.getId());
        toBeUpdated.setUserId(entity.getUserId());
        List<Long> clinicIds = toBeUpdated.getClinics().stream().map(ClinicEntity::getId).collect(Collectors.toList());
        ;
        List<ClinicEntity> userClinics = StreamSupport.stream(clinicRepository.findAllById(clinicIds).spliterator(), false)
                .collect(Collectors.toList());
        toBeUpdated.setClinics(userClinics);
        userRepository.save(toBeUpdated);
        updateKeycloakUserService.update(userModel);
    }
}
