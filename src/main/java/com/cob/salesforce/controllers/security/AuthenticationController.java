package com.cob.salesforce.controllers.security;


import com.cob.salesforce.models.security.Credentials;
import com.cob.salesforce.models.security.Tokens;
import com.cob.salesforce.services.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("tokens")
    public Tokens getToken(HttpServletRequest request,
                           @RequestBody Credentials credentials) throws Exception {
        return authenticationService.getTokens(credentials);
    }
}
