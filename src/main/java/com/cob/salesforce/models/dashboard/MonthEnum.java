package com.cob.salesforce.models.dashboard;

public enum MonthEnum {
    JAN("January"),
    FEB("February"),
    MAR("March"),
    APR("April"),
    MAY("May"),
    JUN("June"),
    JUL("July"),
    AUG("August"),
    SEP("September"),
    OCT("October"),
    NOV("November"),
    DEC("December");

    private final String name;

    MonthEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
