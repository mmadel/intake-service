package com.cob.salesforce.enums;

public enum InsuranceWorkerType {
    Comp_NoFault("Comp_NoFault"),
    Commercial("Commercial");
    public final String label;

    private InsuranceWorkerType(String label) {
        this.label = label;
    }
}
