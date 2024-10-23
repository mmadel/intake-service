package com.cob.salesforce.repositories.admin.trust.device;

import com.cob.salesforce.entity.admin.trust.device.TrustedDevice;
import org.springframework.data.repository.CrudRepository;

public interface TrustedDeviceRepository extends CrudRepository<TrustedDevice, Long> {
}
