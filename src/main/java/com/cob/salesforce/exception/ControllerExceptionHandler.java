package com.cob.salesforce.exception;

import com.cob.salesforce.exception.business.IntakeException;
import com.cob.salesforce.exception.business.UserKeyCloakException;
import com.cob.salesforce.exception.response.ControllerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.WebApplicationException;
import java.util.Locale;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    ResourceBundleMessageSource messageSource;

    @ExceptionHandler(value = {IntakeException.class})
    public ResponseEntity handleFeedbackExceptionException(IntakeException ex, WebRequest request) {
        String errorMessage = messageSource.getMessage(ex.getCode(), ex.getParameters(), Locale.ENGLISH);
        ControllerErrorResponse controllerErrorResponse = new ControllerErrorResponse(errorMessage, ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus());
        log.error(controllerErrorResponse.getMessage());
        return new ResponseEntity(controllerErrorResponse, ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus());
    }

    @ExceptionHandler(value = {WebApplicationException.class})
    public ResponseEntity handleWebApplicationException(IntakeException ex, WebRequest request) {
        String errorMessage = messageSource.getMessage(ex.getCode(), ex.getParameters(), Locale.ENGLISH);
        ControllerErrorResponse controllerErrorResponse = new ControllerErrorResponse(errorMessage, ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus());
        log.error(controllerErrorResponse.getMessage());
        return new ResponseEntity(controllerErrorResponse, ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus());
    }

    @ExceptionHandler(value = InvalidBearerTokenException.class)
    public ResponseEntity handleInvalidBearerTokenException(InvalidBearerTokenException invalidBearerTokenException) {
        ControllerErrorResponse controllerErrorResponse = new ControllerErrorResponse(invalidBearerTokenException.getMessage(), HttpStatus.UNAUTHORIZED);
        log.error(controllerErrorResponse.getMessage());
        return new ResponseEntity(controllerErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity handleAccessDeniedException(AuthenticationException authenticationException) {
        ControllerErrorResponse controllerErrorResponse = new ControllerErrorResponse(authenticationException.getMessage(), HttpStatus.UNAUTHORIZED);
        log.error(controllerErrorResponse.getMessage());
        return new ResponseEntity<>(controllerErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        ControllerErrorResponse controllerErrorResponse = new ControllerErrorResponse(accessDeniedException.getMessage(), HttpStatus.UNAUTHORIZED);
        log.error(controllerErrorResponse.getMessage());
        return new ResponseEntity<>(controllerErrorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UserKeyCloakException.class})
    public ResponseEntity handleKeyCloak(UserKeyCloakException ex, WebRequest request) {
        String errorMessage = messageSource.getMessage(ex.getCode(), ex.getParameters(), Locale.ENGLISH);
        ControllerErrorResponse controllerErrorResponse = new ControllerErrorResponse(errorMessage, ex.getStatus());
        log.error(controllerErrorResponse.getMessage());
        return new ResponseEntity<>(controllerErrorResponse, ex.getStatus());
    }
}
