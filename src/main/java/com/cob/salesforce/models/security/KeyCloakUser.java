
package com.cob.salesforce.models.security;

import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class KeyCloakUser {

    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
