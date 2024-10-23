package com.cob.salesforce.usecase.trust.device;

import com.cob.salesforce.entity.admin.trust.device.TrustedDevice;
import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.repositories.admin.trust.device.TrustedDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class RevokeDeviceUseCase {
    @Autowired
    TrustedDeviceRepository trustedDeviceRepository;
    @Transactional
    public void revoke(String deviceId) throws TrustDeviceTokenException {
      Optional<TrustedDevice> trustedDevice =  trustedDeviceRepository.findByDeviceId(deviceId);
      if(trustedDevice.isPresent())
          trustedDeviceRepository.deleteByDeviceId(deviceId);
      else
          throw new TrustDeviceTokenException(HttpStatus.CONFLICT, TrustDeviceTokenException.DEVICE_NOT_FOUND, new Object[]{deviceId.toString()});
    }
}
