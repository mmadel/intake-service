package com.cob.salesforce.enums;

public enum Relationship {
    Self("Self"),
    Spouse("Spouse"),
    Child("Child"),
    Other("Other");
    public final String label;

    private Relationship(String label) {
        this.label = label;
    }
}
