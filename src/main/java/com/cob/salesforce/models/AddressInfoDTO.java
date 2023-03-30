
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressInfoDTO {

    private String type;
    private String first;
    private String second;
    private String country;
    private String state;
    private String province;
    private String city;
    private String zipCode;

    public String getFullAddress() {
        return
                "type=" + type +
                        ", first=" + first +
                        ", second=" + second +
                        ", country=" + country +
                        ", state=" + state +
                        ", province=" + province +
                        ", city=" + city +
                        ", zipCode=" + zipCode +
                        '}';
    }
}
