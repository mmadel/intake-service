package com.cob.salesforce.models.admin.insurance;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.models.admin.ClinicModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
public class InsuranceCompanyModel {

    private Long id;

    private String name;

    private String address;

    private Long createdAt;


    private ClinicModel clinic;
}
