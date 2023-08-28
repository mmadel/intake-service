package com.cob.salesforce.services.admin.user;

import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.services.security.KeyCloakUsersFinderService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
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
    public List<UserModel> getAll() {
        List<UserModel> userModels = new ArrayList<>();
        List<UserRepresentation> kcUsers = keyCloakUsersService.getAllUsers();
        kcUsers.forEach(userRepresentation -> {
            UserModel userModel = new UserModel();
            userModel.setName(userRepresentation.getUsername());
            userModel.setUuid(userRepresentation.getId());
            String userRole = keyCloakUsersService.getUSerRole(userModel.getName());
            if (userRole != null) {
                userModel.setUserRole(userRole);
            }

            List<Long> clinicIds = new ArrayList<>();
            userRepository.findByUserId(userRepresentation.getId()).get().forEach(userClinicEntity -> {
                clinicIds.add(userClinicEntity.getClinicId());
            });
            List<ClinicModel> clinicModels = new ArrayList<>();
            clinicRepository.findAllById(clinicIds).forEach(clinicEntity -> {
                clinicModels.add(mapper.map(clinicEntity, ClinicModel.class));
            });
            userModel.setClinics(clinicModels);
            userModels.add(userModel);
        });
        return userModels;
    }

    @Override
    public UserModel getById(String Id) {
        UserRepresentation userRepresentation = keyCloakUsersService.getUser(Id);
        return mapFromKCUserToUserModel(userRepresentation);
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


    private UserModel mapFromKCUserToUserModel(UserRepresentation userRepresentation) {
        UserModel userModel = new UserModel();
        userModel.setUuid(userRepresentation.getId());
        userModel.setName(userRepresentation.getUsername());
        userModel.setAddress(userRepresentation.getAttributes().get("address").get(0));
        userModel.setClinics(findByUserId(userRepresentation.getId()));
        userModel.setUserRole(keyCloakUsersService.getUSerRole(userRepresentation.getUsername()));
        return userModel;
    }
}
