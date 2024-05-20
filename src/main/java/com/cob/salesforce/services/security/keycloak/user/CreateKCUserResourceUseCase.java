package com.cob.salesforce.services.security.keycloak.user;

import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.security.KeyCloakUser;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Component
@Slf4j
public class CreateKCUserResourceUseCase {
    private String userUUID;
    public void create(KeyCloakUser keyCloakUser, RealmResource realmResource) throws UserException {
        UsersResource usersResource = realmResource.users();
        UserRepresentation userRepresentation = createUserRepresentation(keyCloakUser);
        Response response = usersResource.create(userRepresentation);
        try {
            userUUID = CreatedResponseUtil.getCreatedId(response);

        } catch (WebApplicationException ex) {
            log.error("check the Keycloak ", ex.getMessage());
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_NOT_FOUND, new Object[]{keyCloakUser.getUsername()});
        }
    }
    private UserRepresentation createUserRepresentation(KeyCloakUser keyCloakUser) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(keyCloakUser.getUsername());
        userRepresentation.setFirstName(keyCloakUser.getFirstName());
        userRepresentation.setLastName(keyCloakUser.getLastName());
        userRepresentation.setEmail(keyCloakUser.getEmail());
        return userRepresentation;
    }
    public String getUserUUID() {
        return userUUID;
    }
}
