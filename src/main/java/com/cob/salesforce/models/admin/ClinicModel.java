package com.cob.salesforce.models.admin;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClinicModel {

    private Long id;

    private String name;

    private String address;

    private Long createdAt;
}
