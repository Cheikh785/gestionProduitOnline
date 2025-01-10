package org.example.gestionproduitonline.exception;

import java.util.List;

public class NotFoundEntityException extends BadRequestException {
    public NotFoundEntityException(String errorCodes, String message, List<ApiFieldError> fieldErrors) {
        super(errorCodes, message, fieldErrors);
    }
    public NotFoundEntityException(String errorCodes, String message) {
        super(errorCodes, message);
    }
}
