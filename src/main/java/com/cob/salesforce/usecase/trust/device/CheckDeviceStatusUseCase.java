package com.cob.salesforce.usecase.trust.device;

import com.cob.salesforce.entity.admin.trust.device.TrustedDevice;
import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceStatus;
import com.cob.salesforce.repositories.admin.trust.device.TrustedDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CheckDeviceStatusUseCase {
    @Autowired
    TrustedDeviceRepository trustedDeviceRepository;

    public DeviceStatus checkStatus(Integer clinicId, String macAddress) throws TrustDeviceTokenException {
        Optional<TrustedDevice> trustedDevice = trustedDeviceRepository.findByClinicIdAndMacAddress(clinicId, macAddress);
        if (trustedDevice.isPresent())
            return fillDeviceStatus(trustedDevice.get());
        else
            throw new TrustDeviceTokenException(HttpStatus.CONFLICT, TrustDeviceTokenException.DEVICE_IS_NOT_TRUSTED, new Object[]{macAddress.toString(),clinicId.toString()});
    }

    private DeviceStatus fillDeviceStatus(TrustedDevice trustedDevice) {
        return DeviceStatus.builder()
                .deviceId(trustedDevice.getDeviceId())
                .isTrusted(true)
                .createdAt(trustedDevice.getCreatedAt())
                .build();
    }
}
