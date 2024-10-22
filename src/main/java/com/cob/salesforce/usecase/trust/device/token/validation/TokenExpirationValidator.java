package com.cob.salesforce.usecase.trust.device.token.validation;

import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenRequest;
import org.springframework.http.HttpStatus;

public class TokenExpirationValidator extends TokenValidator{
    @Override
    public boolean validate(DeviceTokenRequest tokenRequest) throws TrustDeviceTokenException {
        long currentTimeMillis = System.currentTimeMillis();
        if(tokenRequest.getExpiryDate() > currentTimeMillis)
            return validateNext(tokenRequest);
        else
            throw new TrustDeviceTokenException(HttpStatus.CONFLICT, TrustDeviceTokenException.TOKEN_IS_EXPIRED, new Object[]{tokenRequest.getToken()});
    }
}
