package org.example.gestionproduitonline.exception;

import java.util.List;

public class AlreadyExistEntityException extends BadRequestException {
    public AlreadyExistEntityException(String errorCodes, String message, List<ApiFieldError> fieldErrors) {
        super(errorCodes, message, fieldErrors);
    }
    public AlreadyExistEntityException(String errorCodes, String message) {
        super(errorCodes, message);
    }
}
