package com.cob.salesforce.controllers.admin.trust.device;

import com.cob.salesforce.usecase.trust.device.GenerateTokenUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trusted-device")
public class TrustDeviceController {
    @Autowired
    GenerateTokenUseCase generateTokenUseCase;
    @PostMapping(path = "/generate-token")
    public ResponseEntity generateUniqueToken(@RequestBody Integer clinicId) {
        generateTokenUseCase.generate(clinicId);
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
