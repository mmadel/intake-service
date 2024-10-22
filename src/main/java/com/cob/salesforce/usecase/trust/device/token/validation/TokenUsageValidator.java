package com.cob.salesforce.usecase.trust.device.token.validation;

import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenRequest;
import org.springframework.http.HttpStatus;

public class TokenUsageValidator extends TokenValidator{
    @Override
    public boolean validate(DeviceTokenRequest tokenRequest) throws TrustDeviceTokenException {
        if(!tokenRequest.getIsInUsed())
            return validateNext(tokenRequest);
        else
            throw new TrustDeviceTokenException(HttpStatus.CONFLICT, TrustDeviceTokenException.TOKEN_IS_UNUSED, new Object[]{tokenRequest.getToken()});
    }
}
