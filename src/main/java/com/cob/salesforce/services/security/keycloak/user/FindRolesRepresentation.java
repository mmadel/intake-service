package com.cob.salesforce.services.security.keycloak.user;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindRolesRepresentation {
    public List<RoleRepresentation> getRolesRepresentation(List<String> roles, RealmResource realmResource, ClientRepresentation clientRepresentation) {
        List<RoleRepresentation> roleRepresentation = new ArrayList<>();
        roles.forEach(role -> {
            RoleRepresentation clientRole = realmResource.clients().get(clientRepresentation.getId())
                    .roles().get(role).toRepresentation();
            roleRepresentation.add(clientRole);
        });
        return roleRepresentation;
    }
}
