package com.cob.salesforce.models.integration.nppes;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Basic {
    private String first_name;
    private String last_name;
    private String sole_proprietor;
    private String gender;
    private String enumeration_date;
    private String last_updated;
    private String certification_date;
    private String status;
}
