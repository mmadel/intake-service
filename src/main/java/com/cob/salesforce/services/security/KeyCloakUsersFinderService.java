package com.cob.salesforce.services.security;

import com.cob.salesforce.utils.Encryption;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeyCloakUsersFinderService {
    @Autowired
    Keycloak keycloakService;
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${app.keycloak.client-ui-name}")
    private String clientUI;

    @Autowired
    Encryption encryption;

    public List<UserRepresentation> getAllUsers() {
        return keycloakService.realm(realm)
                .users().list();
    }

    public String getUserId(String userName) {
        return keycloakService.realm(realm)
                .users().list()
                .stream().filter(userRepresentation -> userRepresentation.getUsername().equals(userName))
                .map(UserRepresentation::getId)
                .findFirst()
                .get();
    }

    public String getClientId(String clientName) {
        return keycloakService.realm(realm).clients().findAll()
                .stream()
                .filter(clientRepresentation ->
                        clientRepresentation.getClientId().equals(clientName))
                .map(ClientRepresentation::getId)
                .collect(Collectors.toList()).get(0);
    }


    public String getUSerRole(String userName) {
        List result = keycloakService.realm(realm).users()
                .get(getUserId(userName))
                .roles().clientLevel(getClientId(clientUI)).listAll();
        return result.size() > 0 ? result.get(0).toString() : null;
    }
}