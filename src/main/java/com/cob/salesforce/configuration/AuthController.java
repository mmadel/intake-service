package com.cob.salesforce.configuration;

import com.cob.salesforce.models.admin.security.LoginRequest;
import com.cob.salesforce.services.security.JWTGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    JWTGeneratorService jwtGeneratorService;
    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/token")
    public String generateToken(@RequestBody LoginRequest model) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        model.getUserName(), model.getPassword()));
        return jwtGeneratorService.generateToken(authentication);
    }
}
