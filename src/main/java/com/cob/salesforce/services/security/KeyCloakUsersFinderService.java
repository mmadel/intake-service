package com.cob.salesforce.services.security;

import com.cob.salesforce.utils.Encryption;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class KeyCloakUsersFinderService {
    @Autowired
    Keycloak keycloakService;
    @Value("${keycloak.realm}")
    private String realm;

    @Autowired
    Encryption encryption;
    public List<UserRepresentation> getAllUsers() throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        System.out.println(encryption.decrypt("nBuHlyJTZ9txUjYzboj+5Q=="));
        return keycloakService.realm(realm)
                .users().list();
    }
}
