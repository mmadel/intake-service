package com.cob.salesforce.controllers.admin.trust.device;

import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenRequest;
import com.cob.salesforce.usecase.trust.device.GenerateTokenUseCase;
import com.cob.salesforce.usecase.trust.device.ListTrustedDevicesUseCase;
import com.cob.salesforce.usecase.trust.device.RegisterNewDeviceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trusted-device")
public class TrustDeviceController {
    @Autowired
    GenerateTokenUseCase generateTokenUseCase;
    @Autowired
    RegisterNewDeviceUseCase registerNewDeviceUseCase;
    @Autowired
    ListTrustedDevicesUseCase listTrustedDevicesUseCase;

    @PostMapping(path = "/generate-token")
    public ResponseEntity generateUniqueToken(@RequestBody Integer clinicId) {
        generateTokenUseCase.generate(clinicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity register(@RequestBody DeviceTokenRequest tokenRequest) throws TrustDeviceTokenException {
        registerNewDeviceUseCase.register(tokenRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/validate-token")
    public ResponseEntity validateToken() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/list/clinic-id/{clinicId}")
    public ResponseEntity listTrustedDevices(@PathVariable Integer clinicId) throws TrustDeviceTokenException {

        return new ResponseEntity<>(listTrustedDevicesUseCase.list(clinicId), HttpStatus.OK);
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
