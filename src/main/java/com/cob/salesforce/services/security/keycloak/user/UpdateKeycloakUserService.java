package com.cob.salesforce.services.security.keycloak.user;

import com.cob.salesforce.models.admin.user.UserModel;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateKeycloakUserService {
    @Autowired
    Keycloak keycloakService;
    @Value("${kc.realm}")
    private String realm;
    @Autowired
    AssignUserRolesUseCase assignUserRolesUseCase;
    @Autowired
    UnAssignUserRoleUseCase unAssignUserRoleUseCase;

    public void update(UserModel userModel) {
        RealmResource realmResource = keycloakService.realm(realm);
//        if (userModel.getCurrentRole() != null) {
            List assignRoles = new ArrayList();
            assignRoles.add(userModel.getUserRole());
            assignUserRolesUseCase.assign(userModel.getUuid(),assignRoles,realmResource,"edit");
//            List unAssignRoles = new ArrayList();
//            unAssignRoles.add(userModel.getCurrentRole());
//            unAssignUserRoleUseCase.unAssign(userModel.getUuid(),unAssignRoles,realmResource);
//        }
    }
}
