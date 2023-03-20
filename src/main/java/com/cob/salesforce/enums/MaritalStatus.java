package com.cob.salesforce.enums;

public enum MaritalStatus {
    Single("Single"),
    Married("Married"),
    Widowed("Widowed"),
    Divorced("Divorced");

    public final String label;

    private MaritalStatus(String label) {
        this.label = label;
    }
}
