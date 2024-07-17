package com.cob.salesforce.models.integration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasicProvider {
    private String npi;
    private String firstName;
    private String lastName;
    private String displayName;
    private String fullName;
}
