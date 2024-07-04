package com.cob.salesforce.models.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasicAddress {
    private String address;
    private String state;
    private String zipCode;
    private String city;
}
