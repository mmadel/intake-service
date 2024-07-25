package com.cob.salesforce.exception.business;

import org.springframework.http.HttpStatus;

public class ClinicException extends IntakeException{
    public static final String CLINIC_ASSIGN_TO_USER = Category.Business.value() + getPrefix() +"_00";
    public static final String CLINIC_NAME_IS_EXISTS = Category.Business.value() + getPrefix() +"_01";
    public ClinicException(String code) {
        super(code);
    }

    public ClinicException(String code, Object[] parameters) {
        super(code, parameters);
    }

    public ClinicException(HttpStatus status, String code, Object[] parameters) {
        super(status, code, parameters);
    }

    protected static String getPrefix() {
        return "_clinic";
    }
}
