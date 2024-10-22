package com.cob.salesforce.usecase.trust.device;

import com.cob.salesforce.entity.admin.trust.device.DeviceRegistrationRequest;
import com.cob.salesforce.models.admin.trust.device.TrustDeviceToken;
import com.cob.salesforce.repositories.admin.trust.device.DeviceRegistrationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class GenerateTokenUseCase {
    @Autowired
    DeviceRegistrationRequestRepository deviceRegistrationRequestRepository;

    public TrustDeviceToken generate(Integer clinicId) {
        long createdAt = Instant.now().toEpochMilli();
        long expiresAt = createdAt + (10 * 60 * 1000);
        String token = UUID.randomUUID().toString();
        DeviceRegistrationRequest entity = new DeviceRegistrationRequest();
        entity.setToken(token);
        entity.setClinicId(clinicId);
        entity.setCreatedAt(createdAt);
        entity.setExpiresAt(expiresAt);
        entity.setIsUsed(false);
        deviceRegistrationRequestRepository.save(entity);
        return TrustDeviceToken.builder()
                .token(token)
                .expiresAt(expiresAt)
                .build();
    }
}
