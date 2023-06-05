package com.cob.salesforce.enums;

public enum EmergencyRelation {
    Spouse ("Spouse"),
    Friend ("Friend"),
    Father ("Father"),
    Mother ("Mother"),
    Relative  ("Relative"),
    Other  ("Other");
    public final String label;
    private EmergencyRelation(String label) {
        this.label = label;
    }
}
