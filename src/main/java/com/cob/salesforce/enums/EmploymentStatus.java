package com.cob.salesforce.enums;

public enum EmploymentStatus {
    Employed("Employed"),
    Student("Student"),
    Retired("Retired"),
    Other("Other");

    public final String label;

    private EmploymentStatus(String label) {
        this.label = label;
    }
}
