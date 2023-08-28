package com.cob.salesforce.services.security;

import com.cob.salesforce.exception.business.UserKeyCloakException;
import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.security.Credentials;
import com.cob.salesforce.models.security.KeyCloakUser;
import com.cob.salesforce.utils.Encryption;
import com.google.gson.Gson;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class KeyCloakUsersCreatorService {

    @Autowired
    Keycloak keycloakService;
    @Value("${kc.realm}")
    private String realm;
    @Autowired
    Encryption encryption;
    @Autowired
    RestTemplate restTemplate;

    @Value("${kc.administrator.username}")
    private String admin_username;
    @Value("${kc.administrator.password}")
    private String admin_password;
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    KeyCloakUsersRemoverService keyCloakUsersRemoverService;


    public UserRepresentation create(KeyCloakUser keyCloakUser) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, WebApplicationException, UserException, UserKeyCloakException {

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
        setUserPassword(userId, keyCloakUser.getPassword());

        UserResource userResource = usersResource.get(userId);
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(true);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(encryption.decrypt(keyCloakUser.getPassword()));

        updateAttribute(userResource, "address", keyCloakUser.getAddress());
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

    private void setUserPassword(String userId, String password) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UserException, HttpClientErrorException, UserKeyCloakException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authenticationService.getTokens(Credentials.builder()
                .userName(admin_username)
                .password(admin_password)
                .build()).getAccess_token());
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(encryption.decrypt(password));
        Gson gson = new Gson();
        gson.toJson(credential);
        HttpEntity<String> httpEntity = new HttpEntity<>(gson.toJson(credential).toString(), headers);
        try{
            restTemplate.put("http://localhost:8180/admin/realms/patient-intake/users/" + userId + "/reset-password", httpEntity);
        }catch (HttpClientErrorException.BadRequest exception){
            keyCloakUsersRemoverService.removeKCUser(userId);
            String message =   exception.getMessage().split(":")[4].split("\"")[0];
            throw new UserKeyCloakException(HttpStatus.BAD_REQUEST, UserKeyCloakException.INVALID_PASSWORD, new Object[]{message});
        }


    }

    public void update(KeyCloakUser keyCloakUser) throws UserException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        RealmResource realmResource = keycloakService.realm(realm);
        UsersResource usersResource = realmResource.users();
        UserResource userResource = null;
        try {
            userResource = usersResource.get(keyCloakUser.getUserId());
        } catch (WebApplicationException ex) {
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_NOT_FOUND, new Object[]{keyCloakUser.getUsername()});
        }
        if (keyCloakUser.getPassword() != null) {
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setTemporary(true);
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(encryption.decrypt(keyCloakUser.getPassword()));
            userResource.resetPassword(credential);

        }
        updateAttribute(userResource, "address", keyCloakUser.getAddress());

        ClientRepresentation clientRepresentation = realmResource.clients().findByClientId("intake-ui").get(0);
        RolesResource oldRole = null;
        try {
            oldRole = realmResource.clients().get(clientRepresentation.getId()).roles();
        } catch (WebApplicationException ex) {
            throw new IllegalArgumentException("Old role not found");
        }
        userResource.roles().clientLevel(clientRepresentation.getId()).remove(oldRole.list());
        List<RoleRepresentation> role = null;
        try {
            role = prepareRoleRepresentation(keyCloakUser.getRoles(), realmResource, clientRepresentation);
        } catch (WebApplicationException ex) {
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_ROLE_NOT_FOUND, new Object[]{keyCloakUser.getRoles().get(0)});
        }
        userResource.roles().clientLevel(clientRepresentation.getId()).add(role);

    }

    private void updateAttribute(UserResource userResource, String property, String value) {
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.singleAttribute(property, value);
        userResource.update(userRepresentation);
    }

    private UserRepresentation prepareUserRepresentation(KeyCloakUser keyCloakUser) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(keyCloakUser.getUsername());
        user.setFirstName(keyCloakUser.getFirstName());
        user.setLastName(keyCloakUser.getLastName());
        user.setEmail(keyCloakUser.getEmail());
//        CredentialRepresentation credential = new CredentialRepresentation();
//        credential.setTemporary(false);
//        credential.setType(CredentialRepresentation.PASSWORD);
//        credential.setValue(encryption.decrypt(keyCloakUser.getPassword()));
//        user.setCredentials(Arrays.asList(credential));
        return user;
    }

    private List<RoleRepresentation> prepareRoleRepresentation(List<String> roles, RealmResource realmResource, ClientRepresentation clientRepresentation) {
        List<RoleRepresentation> roleRepresentation = new ArrayList<>();
        roles.forEach(role -> {
            RoleRepresentation clientRole = realmResource.clients().get(clientRepresentation.getId())
                    .roles().get(role).toRepresentation();
            roleRepresentation.add(clientRole);
        });
        return roleRepresentation;
    }
}
