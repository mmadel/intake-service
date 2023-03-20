
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


    public String getFullAddress() {
        return
                "country='" + country + '\'' +
                        ", first='" + first + '\'' +
                        ", second='" + second + '\'' +
                        ", type='" + type + '\'' +
                        ", zipCode='" + zipCode + '\'';
    }
}
