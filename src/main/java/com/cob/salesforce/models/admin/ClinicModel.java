package com.cob.salesforce.models.admin;

import com.cob.salesforce.models.common.BasicAddress;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClinicModel {

    private Long id;

    private String name;

    private String address;

    private BasicAddress clinicAddress;
    private Long createdAt;
    private Boolean status;
}
