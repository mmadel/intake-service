package com.cob.salesforce.enums;

public enum PhoneType {
    Home("Home"),
    Work("Work"),
    Other("Other");
    public final String label;

    private PhoneType(String label) {
        this.label = label;
    }

}
