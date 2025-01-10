package org.example.gestionproduitonline.exception;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private final String errorCodes;
    private List<ApiFieldError> fieldErrors;

    public BadRequestException(String errorCodes, String message, List<ApiFieldError> fieldErrors) {
        super(message);
        this.errorCodes = errorCodes;
        this.fieldErrors = fieldErrors;
    }

    public BadRequestException(String errorCodes, String message) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public String getErrorCodes() {
        return errorCodes;
    }

    // Getter pour fieldErrors
    public List<ApiFieldError> getFieldErrors() {
        return fieldErrors;
    }

    // Setter pour fieldErrors
    public void setFieldErrors(List<ApiFieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
