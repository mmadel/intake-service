package com.cob.salesforce.models.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Builder
public class ClinicModel {

    private Long id;

    private String name;

    private String address;

    private Long createdAt;
}
