package com.cob.salesforce.enums;

public enum Gender {
    Male("Male"),
    Female("Female"),
    NonBinary("NonBinary"),
    Self_Describe("to-self"),
    Not_Prefer("not-say");
    public final String label;

    Gender(String label) {
        this.label = label;
    }

}
