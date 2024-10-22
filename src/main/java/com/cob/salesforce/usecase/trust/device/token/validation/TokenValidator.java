package com.cob.salesforce.usecase.trust.device.token.validation;

import com.cob.salesforce.exception.business.TrustDeviceTokenException;
import com.cob.salesforce.models.admin.trust.device.DeviceTokenRequest;

public abstract class TokenValidator  {
    private TokenValidator next;

    public static TokenValidator register(TokenValidator first, TokenValidator... chain) {
        TokenValidator head = first;
        for (TokenValidator nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean validate(DeviceTokenRequest tokenRequest) throws TrustDeviceTokenException;

    protected boolean validateNext(DeviceTokenRequest tokenRequest) throws TrustDeviceTokenException {
        if (next == null) {
            return true;
        }
        return next.validate(tokenRequest);
    }
}
