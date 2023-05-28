package com.cob.salesforce.exception;

import com.cob.salesforce.exception.business.IntakeException;
import com.cob.salesforce.exception.model.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IntakeException.class)
    protected ResponseEntity<Object> handleClinicalException(
            IntakeException ex) {
        return buildResponseEntity(ex);
    }

    private ResponseEntity<Object> buildResponseEntity(IntakeException exception) {
        ErrorModel errorModel = new ErrorModel(exception);
        logger.error(errorModel.getMessage());
        errorModel.getException().printStackTrace();
        return new ResponseEntity<>(errorModel, errorModel.getStatus());
    }

    private ResponseEntity<Object> buildSecurityResponseEntity(AccessDeniedException exception) {
        ErrorModel errorModel = new ErrorModel(exception);
        return new ResponseEntity<>(errorModel, errorModel.getStatus());
    }
}
