
package com.cob.salesforce.models.security;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class KeyCloakUser {

    @SerializedName("access")
    private Access mAccess;
    @SerializedName("createdTimestamp")
    private Long mCreatedTimestamp;
    @SerializedName("disableableCredentialTypes")
    private List<Object> mDisableableCredentialTypes;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("emailVerified")
    private Boolean mEmailVerified;
    @SerializedName("enabled")
    private Boolean mEnabled;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("id")
    private String mId;
    @SerializedName("lastName")
    private String mLastName;
    @SerializedName("notBefore")
    private Long mNotBefore;
    @SerializedName("requiredActions")
    private List<Object> mRequiredActions;
    @SerializedName("totp")
    private Boolean mTotp;
    @SerializedName("username")
    private String mUsername;

    public Access getAccess() {
        return mAccess;
    }

    public void setAccess(Access access) {
        mAccess = access;
    }

    public Long getCreatedTimestamp() {
        return mCreatedTimestamp;
    }

    public void setCreatedTimestamp(Long createdTimestamp) {
        mCreatedTimestamp = createdTimestamp;
    }

    public List<Object> getDisableableCredentialTypes() {
        return mDisableableCredentialTypes;
    }

    public void setDisableableCredentialTypes(List<Object> disableableCredentialTypes) {
        mDisableableCredentialTypes = disableableCredentialTypes;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Boolean getEmailVerified() {
        return mEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        mEmailVerified = emailVerified;
    }

    public Boolean getEnabled() {
        return mEnabled;
    }

    public void setEnabled(Boolean enabled) {
        mEnabled = enabled;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Long getNotBefore() {
        return mNotBefore;
    }

    public void setNotBefore(Long notBefore) {
        mNotBefore = notBefore;
    }

    public List<Object> getRequiredActions() {
        return mRequiredActions;
    }

    public void setRequiredActions(List<Object> requiredActions) {
        mRequiredActions = requiredActions;
    }

    public Boolean getTotp() {
        return mTotp;
    }

    public void setTotp(Boolean totp) {
        mTotp = totp;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
