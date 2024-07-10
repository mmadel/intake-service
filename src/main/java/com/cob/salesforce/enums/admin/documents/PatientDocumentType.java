package com.cob.salesforce.enums.admin.documents;

public enum PatientDocumentType {
    ID("Id"),
    INSURANCE("insurance");
    public final String label;

    private PatientDocumentType(String label) {
        this.label = label;
    }
}
