package com.cob.salesforce.exception.business;

import org.springframework.http.HttpStatus;

public class TrustDeviceTokenException extends IntakeException{
    public static final String TOKEN_IS_NO_EXISTS = Category.Business.value() + getPrefix() + "_00";
    public static final String TOKEN_IS_EXPIRED = Category.Business.value() + getPrefix() + "_01";
    public static final String TOKEN_IS_UNUSED = Category.Business.value() + getPrefix() + "_02";
    public static final String NO_TRUST_DEVICES = Category.Business.value() + getPrefix() + "_03";
    public static final String DEVICE_NOT_FOUND = Category.Business.value() + getPrefix() + "_04";
    public static final String DEVICE_IS_NOT_TRUSTED = Category.Business.value() + getPrefix() + "_05";
    public TrustDeviceTokenException(String code) {
        super(code);
    }
    public TrustDeviceTokenException(String code, Object[] parameters) {
        super(code, parameters);
    }

    public TrustDeviceTokenException(HttpStatus status, String code, Object[] parameters) {
        super(status, code, parameters);
    }

    protected static String getPrefix() {
        return "_token";
    }
}
