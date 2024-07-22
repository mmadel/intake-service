package com.cob.salesforce.models.admin.user;

import com.cob.salesforce.enums.admin.UserRole;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.common.AddressModel;
import com.cob.salesforce.models.common.BasicAddress;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserModel {
    private Long id;
    private String uuid;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private BasicAddress address;
    private String userRole;
    private List<ClinicModel> clinics;
    private Long createdAt;
    private String currentRole;
}
