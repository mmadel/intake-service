package com.cob.salesforce.models.validation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressInfoRequired {
    Long id;
    Boolean type;
    Boolean first;
    Boolean second;
    Boolean country;
    Boolean zipCode;
}
