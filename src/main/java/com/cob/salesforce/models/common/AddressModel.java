package com.cob.salesforce.models.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressModel {
    private String address;
    private String state;
    private String city;
    private String zipCode;

}
