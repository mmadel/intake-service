package com.cob.salesforce.services.security.keycloak.user.validation;


import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.security.KeyCloakUser;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;

import java.util.List;

public class UserEmailValidator extends KeyCloakUserValidator{
    @Override
    public boolean validate(KeyCloakUser keyCloakUser) throws UserException {
        List<UserRepresentation> userRepresentations = keyCloakUser.getRealmResource().users().list();
        for (UserRepresentation userRepresentation : userRepresentations) {
            if (userRepresentation.getEmail() != null && userRepresentation.getEmail().equals(keyCloakUser.getEmail()))
                throw new UserException(HttpStatus.CONFLICT, UserException.USER_EMAIL_IS_EXISTS, new Object[]{keyCloakUser.getEmail()});
        }
        return validateNext(keyCloakUser);
    }
}
