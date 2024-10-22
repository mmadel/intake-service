package com.cob.salesforce.controllers.admin.trust;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trusted-device")
public class TrustDeviceController {

    @PostMapping(path = "/generate-token")
    public ResponseEntity generateUniqueToken() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity register() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/validate-token")
    public ResponseEntity validateToken() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public ResponseEntity listTrustedDevices() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/revoke")
    public ResponseEntity revokeTrustedDevice() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/status")
    public ResponseEntity getDeviceStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
