package com.cob.salesforce.usecase.trust.device;

import com.cob.salesforce.entity.admin.trust.device.DeviceRegistrationRequest;
import com.cob.salesforce.entity.admin.trust.device.TrustDeviceStatus;
import com.cob.salesforce.entity.admin.trust.device.TrustedDevice;
import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenRequest;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenResponse;
import com.cob.salesforce.repositories.admin.trust.device.DeviceRegistrationRequestRepository;
import com.cob.salesforce.repositories.admin.trust.device.TrustedDeviceRepository;
import com.cob.salesforce.usecase.trust.device.token.validation.TokenExistsValidator;
import com.cob.salesforce.usecase.trust.device.token.validation.TokenExpirationValidator;
import com.cob.salesforce.usecase.trust.device.token.validation.TokenUsageValidator;
import com.cob.salesforce.usecase.trust.device.token.validation.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterNewDeviceUseCase {
    @Autowired
    TrustedDeviceRepository trustedDeviceRepository;
    @Autowired
    DeviceRegistrationRequestRepository deviceRegistrationRequestRepository;

    public void register(DeviceTokenRequest tokenRequest) throws TrustDeviceTokenException {
        TokenValidator tokenValidator = TokenValidator.register(
                new TokenExistsValidator(),
                new TokenExpirationValidator(),
                new TokenUsageValidator()
        );
        tokenValidator.validate(tokenRequest);
        DeviceRegistrationRequest deviceRegistrationRequest = deviceRegistrationRequestRepository.findByToken(tokenRequest.getToken()).get();
        String deviceId = UUID.randomUUID().toString();
        buildEntity(deviceId, deviceRegistrationRequest.getClinicId(), tokenRequest);
        deviceRegistrationRequest.setIsUsed(true);
        deviceRegistrationRequestRepository.save(deviceRegistrationRequest);
        DeviceTokenResponse.builder()
                .deviceId(deviceId)
                .isTrusted(true);
    }

    private void buildEntity(String deviceId, Integer clinicId, DeviceTokenRequest tokenRequest) {
        TrustedDevice trustedDevice = new TrustedDevice();
        trustedDevice.setClinicId(clinicId);
        trustedDevice.setToken(tokenRequest.getToken());
        trustedDevice.setDeviceId(deviceId);
        trustedDevice.setStatus(TrustDeviceStatus.trusted);
        trustedDevice.setDeviceName(tokenRequest.getDeviceInformation().getDeviceName());
        trustedDevice.setMacAddress(tokenRequest.getDeviceInformation().getMacAddress());
        trustedDeviceRepository.save(trustedDevice);
    }
}
