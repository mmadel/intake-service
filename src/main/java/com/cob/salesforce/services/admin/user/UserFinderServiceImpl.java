package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.UserEntity;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.services.security.KeyCloakUsersFinderService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserFinderServiceImpl implements UserFinderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ModelMapper mapper;

    @Autowired
    KeyCloakUsersFinderService keyCloakUsersService;

    @Override
    @Cacheable("users")
    public List<UserModel> getAll() {
        log.info("get all users");
        List<UserModel> userModels = new ArrayList<>();
        List<UserRepresentation> kcUsers = keyCloakUsersService.getAllUsers();
        List<String> userRepresentationIdList = new ArrayList<>();
        kcUsers.forEach(userRepresentation -> {
            UserModel userModel = new UserModel();
            userModel.setName(userRepresentation.getUsername());
            userModel.setUuid(userRepresentation.getId());
            userModel.setFirstName(userRepresentation.getFirstName());
            userModel.setLastName(userRepresentation.getLastName());
            userModel.setEmail(userRepresentation.getEmail());
            String userRole = keyCloakUsersService.getUSerRole(userModel.getName());
            if (userRole != null) {
                userModel.setUserRole(userRole);
            }
            userRepresentationIdList.add(userRepresentation.getId());
            userModels.add(userModel);
        });
        List<String> userModelsUUIDs = userModels.stream().map(UserModel::getUuid).collect(Collectors.toList());
        List<String> userEntitiesUUIDs = userRepository.findByUsers(userModelsUUIDs).stream().map(userEntity -> userEntity.getUserId()).collect(Collectors.toList());
        return userModels.stream()
                .filter(userModel -> userEntitiesUUIDs.contains(userModel.getUuid()))
                .collect(Collectors.toList());
    }

    @Override
    public UserModel getById(String Id) {
        UserRepresentation userRepresentation = keyCloakUsersService.getUser(Id);
        return mapFromKCUserToUserModel(userRepresentation);
    }


    @Override
    public List<ClinicModel> findByUserId(String userId) {
        List<ClinicEntity> clinicEntities = userRepository.findByUserId(userId).get().getClinics();
        return clinicEntities.stream()
                .filter(clinic -> clinic.getStatus())
                .map(clinic -> mapper.map(clinic, ClinicModel.class))
                .collect(Collectors.toList());
    }


    private UserModel mapFromKCUserToUserModel(UserRepresentation userRepresentation) {
        UserModel userModel = new UserModel();
        userModel.setUuid(userRepresentation.getId());
        userModel.setName(userRepresentation.getUsername());
        userModel.setFirstName(userRepresentation.getFirstName());
        userModel.setLastName(userRepresentation.getLastName());
        userModel.setEmail(userRepresentation.getEmail());
        UserEntity user = userRepository.findByUserId(userRepresentation.getId()).get();
        userModel.setAddress(user.getAddress());
        List<ClinicModel> clinics = user.getClinics().stream().map(clinic -> mapper.map(clinic, ClinicModel.class)).collect(Collectors.toList());
        userModel.setClinics(clinics);
        userModel.setUserRole(keyCloakUsersService.getUSerRole(userRepresentation.getUsername()));
        return userModel;
    }
}
