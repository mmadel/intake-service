package com.cob.salesforce.models.admin.trust.device;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceInformation {
    private String macAddress;
    private String deviceName;
    private String ipAddress;
}
