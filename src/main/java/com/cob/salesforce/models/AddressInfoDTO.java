
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressInfoDTO {

    private String country;

    private String first;

    private String second;

    private String type;
    private String zipCode;
}
