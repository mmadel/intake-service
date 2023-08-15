package com.cob.salesforce.services.security;

import com.cob.salesforce.models.security.Credentials;
import com.cob.salesforce.models.security.KeyCloakUser;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class KeyCloakUsersService {
    @Autowired
    Keycloak keycloakService;
    @Value("${keycloak.realm}")
    private String realm;

    public List<UserRepresentation> getAllUsers(){
        return keycloakService.realm(realm)
                .users().list();
    }
}
