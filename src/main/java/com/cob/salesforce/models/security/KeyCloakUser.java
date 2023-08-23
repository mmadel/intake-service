
package com.cob.salesforce.models.security;

import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyCloakUser {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String address ;
    private List<String> roles;

}
