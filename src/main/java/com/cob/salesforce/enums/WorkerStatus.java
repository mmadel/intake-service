package com.cob.salesforce.enums;

public enum WorkerStatus {
    FullTime("FullTime"),
    PartTime("PartTime"),
    NotWork("NotWork");

    public final String label;

    private WorkerStatus(String label) {
        this.label = label;
    }
}
