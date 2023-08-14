package com.cob.salesforce.controllers.security;

import com.cob.salesforce.services.security.KeyCloakUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kc/users")
public class KeyCloakUserController {
    @Autowired
    KeyCloakUsersService keyCloakUsersService;

    @GetMapping(path = "/find")
    @ResponseBody
    public ResponseEntity getAll() {
        return new ResponseEntity(keyCloakUsersService.getAllUsers(), HttpStatus.OK);
    }
}
