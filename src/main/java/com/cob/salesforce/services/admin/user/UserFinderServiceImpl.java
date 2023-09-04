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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<String> userRepresentationIdList = new ArrayList<>();
        kcUsers.forEach(userRepresentation -> {
            UserModel userModel = new UserModel();
            userModel.setName(userRepresentation.getUsername());
            userModel.setUuid(userRepresentation.getId());
            String userRole = keyCloakUsersService.getUSerRole(userModel.getName());
            if (userRole != null) {
                userModel.setUserRole(userRole);
            }
            userRepresentationIdList.add(userRepresentation.getId());
            userModels.add(userModel);
        });
        List<Long> clinicIds = new ArrayList<>();
        Map<String, List<ClinicModel>> userClinicMap = new HashMap<>();
        userRepository.findByUsers(userRepresentationIdList).forEach(userClinicEntity -> {
            clinicIds.add(userClinicEntity.getClinicId());
            List<ClinicModel> clinics = userClinicMap.get(userClinicEntity.getUserId());
            if (clinics == null) {
                List<ClinicModel> list = new ArrayList<>();
                ClinicModel clinicModel = new ClinicModel();
                clinicModel.setId(userClinicEntity.getClinicId());
                list.add(clinicModel);
                userClinicMap.put(userClinicEntity.getUserId(), list);
            } else {
                ClinicModel clinicModel = new ClinicModel();
                clinicModel.setId(userClinicEntity.getClinicId());
                clinics.add(clinicModel);
                userClinicMap.put(userClinicEntity.getUserId(), clinics);
            }
        });

        clinicRepository.findAllById(clinicIds).forEach(clinicEntity -> {
            userClinicMap.entrySet().stream().forEach(stringListEntry -> {
                List<ClinicModel> list = stringListEntry.getValue();
                list.forEach(model -> {
                    if (model.getId().equals(clinicEntity.getId())) {
                        model.setName(clinicEntity.getName());
                        model.setAddress(clinicEntity.getAddress());
                    }
                });
            });
        });
        userModels.forEach(model -> {
            userClinicMap.entrySet().stream().forEach(stringListEntry -> {
                if (stringListEntry.getKey().equals(model.getUuid()))
                    model.setClinics(stringListEntry.getValue());
            });
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
