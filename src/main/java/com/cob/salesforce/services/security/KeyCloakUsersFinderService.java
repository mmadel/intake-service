package com.cob.salesforce.services.security;

import com.cob.salesforce.utils.Encryption;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyCloakUsersFinderService {
    @Autowired
    Keycloak keycloakService;
    @Value("${keycloak.realm}")
    private String realm;

    @Autowired
    Encryption encryption;
    public List<UserRepresentation> getAllUsers() {
        return keycloakService.realm(realm)
                .users().list();
    }
}
