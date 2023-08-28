package com.cob.salesforce.controllers.security;

import com.cob.salesforce.exception.business.UserKeyCloakException;
import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.security.KeyCloakUser;
import com.cob.salesforce.services.security.KeyCloakUsersCreatorService;
import com.cob.salesforce.services.security.KeyCloakUsersFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/kc/users")
public class KeyCloakUserController {
    @Autowired
    KeyCloakUsersFinderService keyCloakUsersService;
    @Autowired
    KeyCloakUsersCreatorService keyCloakUsersCreatorService;

    @GetMapping(path = "/find")
    @ResponseBody
    public ResponseEntity getAll() throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return new ResponseEntity(keyCloakUsersService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    @ResponseBody
    public ResponseEntity create(@RequestBody KeyCloakUser keyCloakUser) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UserException, UserKeyCloakException {
        return new ResponseEntity(keyCloakUsersCreatorService.create(keyCloakUser), HttpStatus.OK);
    }

}
