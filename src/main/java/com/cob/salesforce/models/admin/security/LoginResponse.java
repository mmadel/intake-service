package com.cob.salesforce.models.admin.security;

import com.cob.salesforce.models.admin.user.UserModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginResponse {
    private String accessToken;
    private String userRole;
    private Long  userId;
}
