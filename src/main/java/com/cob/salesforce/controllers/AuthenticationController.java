package com.cob.salesforce.controllers;

import com.cob.salesforce.models.admin.security.LoginRequest;
import com.cob.salesforce.models.admin.security.LoginResponse;
import com.cob.salesforce.models.admin.security.SecurityUser;
import com.cob.salesforce.services.security.JWTGeneratorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    JWTGeneratorService jwtGeneratorService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ModelMapper mapper;

    @PostMapping("/login")
    public ResponseEntity generateToken(@RequestBody LoginRequest model) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        model.getUserName(), model.getPassword()));
        return new ResponseEntity(LoginResponse.builder()
                .accessToken(jwtGeneratorService.generateToken(authentication))
                .userId(((SecurityUser) authentication.getPrincipal()).user.getId())
                .build(), HttpStatus.OK);
    }
}
