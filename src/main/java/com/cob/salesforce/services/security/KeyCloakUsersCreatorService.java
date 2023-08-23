package com.cob.salesforce.services.security;

import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.security.KeyCloakUser;
import com.cob.salesforce.utils.Encryption;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class KeyCloakUsersCreatorService {

    @Autowired
    Keycloak keycloakService;
    @Value("${kc.realm}")
    private String realm;
    @Autowired
    Encryption encryption;


    public UserRepresentation create(KeyCloakUser keyCloakUser) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, WebApplicationException, UserException {

        RealmResource realmResource = keycloakService.realm(realm);
        UsersResource usersResource = realmResource.users();
        UserRepresentation user = prepareUserRepresentation(keyCloakUser);
        Response response = usersResource.create(user);
        String userId = null;
        try {
            userId = CreatedResponseUtil.getCreatedId(response);
        } catch (WebApplicationException ex) {
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_IS_EXISTS, new Object[]{keyCloakUser.getUsername()});
        }

        UserResource userResource = usersResource.get(userId);
        updateAttribute(userResource,"address" ,keyCloakUser.getAddress());
        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId("intake-ui").get(0);
        List<RoleRepresentation> roles = null;
        try {
            roles = prepareRoleRepresentation(keyCloakUser.getRoles(), realmResource, clientRepresentation);
        } catch (WebApplicationException ex) {
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_ROLE_NOT_FOUND, new Object[]{keyCloakUser.getRoles().get(0)});
        }
        userResource.roles().clientLevel(clientRepresentation.getId()).add(roles);
        return userResource.toRepresentation();
    }

    private void updateAttribute(UserResource userResource, String property, String value) {
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.singleAttribute(property,value);
        userResource.update(userRepresentation);
    }
    private UserRepresentation prepareUserRepresentation(KeyCloakUser keyCloakUser) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(keyCloakUser.getUsername());
        user.setFirstName(keyCloakUser.getFirstName());
        user.setLastName(keyCloakUser.getLastName());
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(encryption.decrypt(keyCloakUser.getPassword()));
        user.setEmail(keyCloakUser.getEmail());
        user.setCredentials(Arrays.asList(credential));
        return user;
    }

    private List<RoleRepresentation> prepareRoleRepresentation(List<String> roles, RealmResource realmResource, ClientRepresentation clientRepresentation) {
        List<RoleRepresentation>  roleRepresentation= new ArrayList<>();
        roles.forEach(role -> {
            RoleRepresentation clientRole = realmResource.clients().get(clientRepresentation.getId())
                    .roles().get(role).toRepresentation();
            roleRepresentation.add(clientRole);
        });
        return roleRepresentation;
    }
}
