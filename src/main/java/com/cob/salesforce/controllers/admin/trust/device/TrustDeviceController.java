package com.cob.salesforce.controllers.admin.trust.device;

import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenRequest;
import com.cob.salesforce.usecase.trust.device.*;
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
    @Autowired
    RevokeDeviceUseCase revokeDeviceUseCase;
    @Autowired
    CheckDeviceStatusUseCase checkDeviceStatusUseCase;

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

    @DeleteMapping(path = "/revoke/device-id/{deviceId}")
    public ResponseEntity revokeTrustedDevice(@PathVariable String deviceId) throws TrustDeviceTokenException {
        revokeDeviceUseCase.revoke(deviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/status/clinic-id/{clinicId}/mac-address/{macAddress}")
    public ResponseEntity getDeviceStatus(@PathVariable Integer clinicId , @PathVariable String macAddress) throws TrustDeviceTokenException {
        return new ResponseEntity<>(checkDeviceStatusUseCase.checkStatus(clinicId,macAddress),HttpStatus.OK);
    }
}
