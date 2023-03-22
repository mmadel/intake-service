package com.cob.salesforce.enums;

public enum CaseStatus {

    Active("Active"),
    InActive("InActive");

    public final String label;

    private CaseStatus(String label) {
        this.label = label;
    }
}
