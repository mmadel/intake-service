package com.cob.salesforce.models.admin.trust.device;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceTokenResponse {
    private String deviceId;
    private Boolean isTrusted;
}
