package com.cob.salesforce.repositories.admin.trust.device;

import com.cob.salesforce.entity.admin.trust.device.DeviceRegistrationRequest;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRegistrationRequestRepository extends CrudRepository<DeviceRegistrationRequest, Long> {
}
