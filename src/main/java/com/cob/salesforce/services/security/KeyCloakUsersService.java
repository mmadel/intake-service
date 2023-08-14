package com.cob.salesforce.services.security;

import com.cob.salesforce.models.security.Credentials;
import com.cob.salesforce.models.security.KeyCloakUser;
import com.cob.salesforce.models.security.Tokens;
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
    AuthenticationService authenticationService;
    @Value("${app.keycloak.administrator.username}")
    private String administratorUserName;
    @Value("${app.keycloak.administrator.password}")
    private String administratorPassword;
    @Value("${app.keycloak.users.url}")
    private String usersUrl;
    @Autowired
    RestTemplate restTemplate;

    public List<KeyCloakUser> getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authenticationService.getTokens(Credentials.builder()
                .userName(administratorUserName)
                .password(administratorPassword)
                .build()).getAccess_token());
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<List<KeyCloakUser>> response = restTemplate.exchange(usersUrl, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<KeyCloakUser>>() {
        });
        return response.getBody();
    }
}
