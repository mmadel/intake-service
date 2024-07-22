package com.cob.salesforce.services.security.keycloak.user;

import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.models.security.KeyCloakUser;
import com.cob.salesforce.services.security.keycloak.user.validation.KeyCloakUserValidator;
import com.cob.salesforce.services.security.keycloak.user.validation.UserEmailValidator;
import com.cob.salesforce.services.security.keycloak.user.validation.UserExistsValidator;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
@Slf4j
public class CreateKeycloakUserService {
    @Autowired
    Keycloak keycloakService;

    @Value("${kc.realm}")
    private String realm;

    @Autowired
    CreateKCUserResourceUseCase createKCUserResourceUseCase;
    @Autowired
    CreateUserCredentialsUseCase createUserCredentialsUseCase;
    @Autowired
    AssignUserRolesUseCase assignUserRolesUseCase;

    public void create(UserModel userModel) throws UserException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        RealmResource realmResource = keycloakService.realm(realm);
        KeyCloakUser keyCloakUser = convertToKeycloakUser(userModel);
        keyCloakUser.setRealmResource(realmResource);
        validateUser(keyCloakUser);
        createKCUserResourceUseCase.create(keyCloakUser, realmResource);
        userModel.setUuid(createKCUserResourceUseCase.getUserUUID());
        createUserCredentialsUseCase.create(createKCUserResourceUseCase.getUserUUID(), keyCloakUser.getPassword());
        assignUserRolesUseCase.assign(createKCUserResourceUseCase.getUserUUID(), keyCloakUser.getRoles(), realmResource);
    }
    public void delete(UserModel model) throws UserException {
        javax.ws.rs.core.Response response = keycloakService.realm(realm)
                .users().delete(model.getUuid());
        if (response.getStatus() == 404)
            throw new UserException(HttpStatus.NOT_FOUND, UserException.USER_NOT_FOUND, new Object[]{model.getUuid()});
    }

    private KeyCloakUser convertToKeycloakUser(UserModel userModel) {
        return KeyCloakUser.builder()
                .username(userModel.getName())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .roles(Arrays.asList(userModel.getUserRole()))
                .build();
    }

    private void validateUser(KeyCloakUser keyCloakUser) throws UserException {
        KeyCloakUserValidator validator = KeyCloakUserValidator.register(
                new UserExistsValidator(),
                new UserEmailValidator()
        );
        validator.validate(keyCloakUser);
    }
}
