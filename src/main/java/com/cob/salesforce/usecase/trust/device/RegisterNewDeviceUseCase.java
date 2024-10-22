package com.cob.salesforce.usecase.trust.device;

import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenRequest;
import com.cob.salesforce.usecase.trust.device.token.validation.TokenExistsValidator;
import com.cob.salesforce.usecase.trust.device.token.validation.TokenExpirationValidator;
import com.cob.salesforce.usecase.trust.device.token.validation.TokenUsageValidator;
import com.cob.salesforce.usecase.trust.device.token.validation.TokenValidator;
import org.springframework.stereotype.Component;

@Component
public class RegisterNewDeviceUseCase {
    public void register(DeviceTokenRequest tokenRequest) throws TrustDeviceTokenException {
        TokenValidator tokenValidator =TokenValidator.register(
                new TokenExistsValidator(),
                new TokenExpirationValidator(),
                new TokenUsageValidator()
        );
        tokenValidator.validate(tokenRequest);
    }
}
