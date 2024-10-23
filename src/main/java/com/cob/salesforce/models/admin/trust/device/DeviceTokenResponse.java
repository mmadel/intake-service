package com.cob.salesforce.models.admin.trust.device;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DeviceTokenResponse {
    private String deviceId;
    private Boolean isTrusted;
}
