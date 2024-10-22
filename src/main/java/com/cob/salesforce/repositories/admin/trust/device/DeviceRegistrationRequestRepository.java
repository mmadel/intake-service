package com.cob.salesforce.repositories.admin.trust.device;

import com.cob.salesforce.entity.admin.trust.device.DeviceRegistrationRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeviceRegistrationRequestRepository extends CrudRepository<DeviceRegistrationRequest, Long> {
    Optional<DeviceRegistrationRequest> findByToken(String token);
}
