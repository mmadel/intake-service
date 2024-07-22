package com.cob.salesforce.services.security.keycloak.user;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AssignUserRolesUseCase {
    @Value("${kc.intake.client}")
    private String intakeResource;
    @Autowired
    FindRolesRepresentation findRolesRepresentation;

    public void assign(String uuid, List<String> roles, RealmResource realmResource , String mode) {
        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId(intakeResource).get(0);
        UserResource userResource = getUserResource(uuid, realmResource);
        if(mode =="edit"){
            List<String> oldRoles=userResource.roles().clientLevel(clientRepresentation.getId()).listAll().stream()
                    .map(roleRepresentation -> roleRepresentation.getName())
                    .collect(Collectors.toList());
            boolean allOldRolesIncluded = oldRoles.stream().allMatch(roles::contains);
            if(!allOldRolesIncluded){
                List<RoleRepresentation> roleRepresentations = findRolesRepresentation.getRolesRepresentation(roles, realmResource, clientRepresentation);
                List<RoleRepresentation> oldRoleRepresentations = findRolesRepresentation.getRolesRepresentation(oldRoles, realmResource, clientRepresentation);
                userResource.roles().clientLevel(clientRepresentation.getId()).add(roleRepresentations);
                userResource.roles().clientLevel(clientRepresentation.getId()).remove(oldRoleRepresentations);
            }
        }
        if(mode == "create"){
            List<RoleRepresentation> roleRepresentations = findRolesRepresentation.getRolesRepresentation(roles, realmResource, clientRepresentation);
            userResource.roles().clientLevel(clientRepresentation.getId()).add(roleRepresentations);
        }
    }

    private UserResource getUserResource(String uuid, RealmResource realmResource) {
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(uuid);
        return userResource;
    }
}
