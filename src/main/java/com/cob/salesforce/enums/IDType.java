package com.cob.salesforce.enums;

public enum IDType {
    SSN("SSN"),
    Driving_Licence("Drive_licence"),
    Other("Other");

    public final String label;

    private IDType(String label) {
        this.label = label;
    }

}
