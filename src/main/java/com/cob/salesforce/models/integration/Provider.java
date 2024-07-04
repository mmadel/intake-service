package com.cob.salesforce.models.integration;

import com.cob.salesforce.models.common.Address;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class Provider {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String npi;
    private String phone;
    private Address address;
    private ProviderInfo providerInfo;
}
