
package com.cob.salesforce.models.security;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Access {

    @SerializedName("impersonate")
    private Boolean mImpersonate;
    @SerializedName("manage")
    private Boolean mManage;
    @SerializedName("manageGroupMembership")
    private Boolean mManageGroupMembership;
    @SerializedName("mapRoles")
    private Boolean mMapRoles;
    @SerializedName("view")
    private Boolean mView;

    public Boolean getImpersonate() {
        return mImpersonate;
    }

    public void setImpersonate(Boolean impersonate) {
        mImpersonate = impersonate;
    }

    public Boolean getManage() {
        return mManage;
    }

    public void setManage(Boolean manage) {
        mManage = manage;
    }

    public Boolean getManageGroupMembership() {
        return mManageGroupMembership;
    }

    public void setManageGroupMembership(Boolean manageGroupMembership) {
        mManageGroupMembership = manageGroupMembership;
    }

    public Boolean getMapRoles() {
        return mMapRoles;
    }

    public void setMapRoles(Boolean mapRoles) {
        mMapRoles = mapRoles;
    }

    public Boolean getView() {
        return mView;
    }

    public void setView(Boolean view) {
        mView = view;
    }

}
