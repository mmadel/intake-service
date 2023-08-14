package com.cob.salesforce.models.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Credentials {
    private String userName;
    private String password;
}
