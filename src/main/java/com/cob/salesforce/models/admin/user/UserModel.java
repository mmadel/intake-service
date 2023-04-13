package com.cob.salesforce.models.admin.user;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.enums.admin.UserRole;
import com.cob.salesforce.models.admin.ClinicModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
public class UserModel {
    private Long id;

    private String name;
    private String password;

    private String address;


    private UserRole userRole;

    private List<ClinicModel> clinics;
    @Column
    private Long createdAt;
}
