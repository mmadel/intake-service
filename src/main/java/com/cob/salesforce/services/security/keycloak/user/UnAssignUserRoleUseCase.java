package com.cob.salesforce.services.security.keycloak.user;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnAssignUserRoleUseCase {
    @Value("${kc.intake.client}")
    private String intakeResource;
    @Autowired
    FindRolesRepresentation findRolesRepresentation;

    public void unAssign(String uuid, List<String> roles, RealmResource realmResource) {
        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId(intakeResource).get(0);
        List<RoleRepresentation> roleRepresentations = findRolesRepresentation.getRolesRepresentation(roles, realmResource, clientRepresentation);
        UserResource userResource = getUserResource(uuid, realmResource);
        userResource.roles().clientLevel(clientRepresentation.getId()).remove(roleRepresentations);
    }

    private UserResource getUserResource(String uuid, RealmResource realmResource) {
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(uuid);
        return userResource;
    }
}