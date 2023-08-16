package com.cob.salesforce.models.admin.user;

import com.cob.salesforce.enums.admin.UserRole;
import com.cob.salesforce.models.admin.ClinicModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserModel {
    private Long id;
    private String uuid;

    private String name;
    private String password;

    private String address;

    private String userRole;
    private List<ClinicModel> clinics;

    private Long createdAt;
}
