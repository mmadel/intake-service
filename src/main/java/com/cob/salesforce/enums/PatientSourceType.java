package com.cob.salesforce.enums;

public enum PatientSourceType {
    Doctor("doctor"),
    Entity("entity");

    public final String label;

    private PatientSourceType(String label) {
        this.label = label;
    }
}
