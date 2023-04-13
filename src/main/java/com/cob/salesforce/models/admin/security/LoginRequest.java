package com.cob.salesforce.models.admin.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String userName;
    private String password;

}
