package com.cob.salesforce.repositories.admin.trust.device;

import com.cob.salesforce.entity.admin.trust.device.TrustedDevice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TrustedDeviceRepository extends CrudRepository<TrustedDevice, Long> {
    Optional<List<TrustedDevice>> findByClinicId(Integer clinicId);
}
