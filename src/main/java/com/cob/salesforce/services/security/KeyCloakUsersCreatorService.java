package com.cob.salesforce.services.security;

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
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;

@Service
public class KeyCloakUsersCreatorService {

    @Autowired
    Keycloak keycloakService;
    @Value("${keycloak.realm}")
    private String realm;
    @Autowired
    Encryption encryption;

    public String create(KeyCloakUser keyCloakUser) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
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
        RealmResource realmResource = keycloakService.realm(realm);
        UsersResource usersResource = realmResource.users();
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);
        UserResource userResource = usersResource.get(userId);
        ClientRepresentation app1Client = realmResource.clients().findByClientId("intake-ui").get(0);
        RoleRepresentation administratorClientRole = realmResource.clients().get(app1Client.getId()) //
                .roles().get("administrator").toRepresentation();
        userResource.roles().clientLevel(app1Client.getId()).add(Arrays.asList(administratorClientRole));
        return userId;
    }
}
