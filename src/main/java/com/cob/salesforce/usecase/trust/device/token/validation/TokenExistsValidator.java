package com.cob.salesforce.usecase.trust.device.token.validation;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.admin.trust.device.DeviceRegistrationRequest;
import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenRequest;
import com.cob.salesforce.repositories.admin.trust.device.DeviceRegistrationRequestRepository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class TokenExistsValidator extends TokenValidator {
    @Override
    public boolean validate(DeviceTokenRequest tokenRequest) throws TrustDeviceTokenException {
        DeviceRegistrationRequestRepository deviceRegistrationRequestRepository = BeanFactory.getBean(DeviceRegistrationRequestRepository.class);
        Optional<DeviceRegistrationRequest> optional = deviceRegistrationRequestRepository.findByToken(tokenRequest.getToken());
        if(optional.isPresent()){
            DeviceRegistrationRequest deviceRegistrationRequest = optional.get();
            tokenRequest.setExpiryDate(deviceRegistrationRequest.getExpiresAt());
            tokenRequest.setIsInUsed(deviceRegistrationRequest.getIsUsed());
            return validateNext(tokenRequest);
        }else{
            throw new TrustDeviceTokenException(HttpStatus.CONFLICT, TrustDeviceTokenException.TOKEN_IS_NO_EXISTS, new Object[]{tokenRequest.getToken()});
        }
    }
}
