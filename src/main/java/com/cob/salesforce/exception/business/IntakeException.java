package com.cob.salesforce.exception.business;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class IntakeException extends Exception  {
    private HttpStatus status;
    private Object[] parameters;
    private String code;

    public IntakeException(String message) {

        super(message);
    }
    public IntakeException(String code, Object[] parameters) {
        super(code);
        this.code = code;
        this.parameters = parameters;
    }
    public IntakeException(HttpStatus status, String code, Object[] parameters) {
        super(code);
        this.code = code;
        this.status = status;
        this.parameters = parameters;
    }
    enum Category {
        Validation("VAL"),
        Business("BUS"),
        System("SYS");

        private String type;

        Category(String type) {
            this.type = type;
        }

        public String value() {
            return type;
        }
    }
}
