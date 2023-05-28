package com.cob.salesforce.exception.response;

import com.cob.salesforce.exception.business.IntakeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class ControllerErrorResponseAdvisor {
    @Autowired
    ResourceBundleMessageSource messageSource;

    public ResponseEntity responseError(IntakeException ex) {
        String errorMessage = messageSource.getMessage(ex.getCode(), ex.getParameters(), Locale.ENGLISH);
        ControllerErrorResponse controllerErrorResponse = new ControllerErrorResponse(errorMessage, ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus());
        log.error(controllerErrorResponse.getMessage());
        return new ResponseEntity(controllerErrorResponse, ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus());
    }
}
