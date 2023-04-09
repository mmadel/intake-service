package com.cob.salesforce.enums.admin;

public enum UserRole {

    Admin("admin"),
    Normal("Normal");
    public final String label;

    private UserRole(String label) {
        this.label = label;
    }
    }
