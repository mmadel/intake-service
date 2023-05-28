package com.cob.salesforce.exception.business;

import org.springframework.http.HttpStatus;

public class PatientException extends IntakeException{
    public static final String PATIENT_GENERAL_ERROR = Category.Business.value() + getPrefix() +"_00";
    public PatientException(String code) {
        super(code);
    }
    public PatientException(String code, Object[] parameters) {
        super(code, parameters);
    }

    public PatientException(HttpStatus status, String code, Object[] parameters) {
        super(status, code, parameters);
    }

    protected static String getPrefix() {
        return "_patient";
    }
}
