package com.cob.salesforce.usecase.trust.device;

import com.cob.salesforce.models.admin.trust.device.TrustDeviceToken;
import org.springframework.stereotype.Component;

@Component
public class GenerateTokenUseCase {
    public TrustDeviceToken generate() {
        return TrustDeviceToken.builder().build();
    }
}
