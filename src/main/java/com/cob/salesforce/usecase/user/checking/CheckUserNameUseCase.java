package com.cob.salesforce.usecase.user.checking;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckUserNameUseCase {
    @Autowired
    Keycloak keycloakService;

    public Boolean checkUserName(String userName)  {
        RealmResource realmResource = keycloakService.realm("COB");
        List<UserRepresentation> users = realmResource.users().search(userName);
        return users.isEmpty();

    }
}
