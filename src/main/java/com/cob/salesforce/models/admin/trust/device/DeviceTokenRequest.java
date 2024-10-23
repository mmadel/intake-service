package com.cob.salesforce.models.admin.trust.device;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceTokenRequest {
    private String token;
    private Long expiryDate;
    private Boolean isInUsed;
    private DeviceInformation deviceInformation;
}