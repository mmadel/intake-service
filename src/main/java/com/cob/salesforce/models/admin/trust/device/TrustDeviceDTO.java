package com.cob.salesforce.models.admin.trust.device;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrustDeviceDTO {
    private String deviceId;
    private String deviceName;
    private String macAddress;
    private Boolean isTrust;
    private Long createdAt;
}
