package com.cob.salesforce.models.intake.fields;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
public class AddressInformation {
    Boolean type;

    Boolean first;

    Boolean second;

    Boolean country;

    Boolean zipCode;
}
