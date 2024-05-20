package com.cob.salesforce.services.security.keycloak.user.validation;


import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.security.KeyCloakUser;

public abstract class KeyCloakUserValidator {
    private KeyCloakUserValidator next;
    public static KeyCloakUserValidator register(KeyCloakUserValidator first , KeyCloakUserValidator ...chain){
        KeyCloakUserValidator head = first;
        for (KeyCloakUserValidator nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }
    public abstract boolean validate(KeyCloakUser keyCloakUser) throws UserException;
    protected boolean validateNext(KeyCloakUser keyCloakUser) throws UserException {
        if (next == null) {
            return true;
        }
        return next.validate(keyCloakUser);
    }
}
