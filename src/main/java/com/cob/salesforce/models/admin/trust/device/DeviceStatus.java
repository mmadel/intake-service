package com.cob.salesforce.models.admin.trust.device;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DeviceStatus {
    private String deviceId;
    private Boolean isTrusted;
    private Long createdAt;
}
