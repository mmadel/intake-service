package com.cob.salesforce.repositories.admin.trust.device;

import com.cob.salesforce.entity.admin.trust.device.TrustedDevice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrustedDeviceRepository extends CrudRepository<TrustedDevice, Long> {
    Optional<List<TrustedDevice>> findByClinicId(Integer clinicId);

    Optional<TrustedDevice> findByDeviceId(String deviceId);

    Optional<TrustedDevice> findByClinicIdAndMacAddress(Integer clinicId, String macAddress);

    @Modifying
    @Query("delete from TrustedDevice td where td.deviceId =:deviceId")
    void deleteByDeviceId(@Param("deviceId") String deviceId);
}
