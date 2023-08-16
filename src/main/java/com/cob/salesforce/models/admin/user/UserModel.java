package com.cob.salesforce.models.admin.user;

import com.cob.salesforce.enums.admin.UserRole;
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
    private List<Long> clinics;

    private Long createdAt;
}
