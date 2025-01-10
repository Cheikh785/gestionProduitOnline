package org.example.gestionproduitonline.exception;

import org.example.gestionproduitonline.constants.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ApiError> buildResponseEntity(String  errorCode, String message, List<ApiFieldError> fieldErrors, HttpStatus status) {
        ApiError apiError = new ApiError();
        apiError.setCode(errorCode);
        apiError.setMessage(message);
        if (fieldErrors != null) {
            apiError.setFields(fieldErrors);
        }
        return new ResponseEntity<>(apiError, status);
    }

    /**
     * Exception handler for NotFoundEntityException.
     * Handles the case when an entity is not found.
     * Returns a ResponseEntity with the appropriate error code, message, and optional field errors.
     * If field errors are present in the exception, they are included in the response; otherwise, null is passed.
     * Returns a ResponseEntity with HTTP status code 400 (Bad Request).
     */
    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<ApiError> handleNotFoundEntityException(NotFoundEntityException exception) {
        return exception.getFieldErrors() != null ?
            buildResponseEntity(exception.getErrorCodes(), exception.getMessage(), exception.getFieldErrors(), HttpStatus.BAD_REQUEST)
            : buildResponseEntity(exception.getErrorCodes(), exception.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for BadRequestException.
     * Handles the case when a request is invalid.
     * Returns a ResponseEntity with the appropriate error code, message, and optional field errors.
     * If field errors are present in the exception, they are included in the response; otherwise, null is passed.
     * Returns a ResponseEntity with HTTP status code 400 (Bad Request).
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException exception) {
        return exception.getFieldErrors() != null ?
                buildResponseEntity(exception.getErrorCodes(), exception.getMessage(), exception.getFieldErrors(), HttpStatus.BAD_REQUEST)
                : buildResponseEntity(exception.getErrorCodes(), exception.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for generic exceptions.
     * Handles any unexpected exceptions that occur during the application execution.
     * Returns a ResponseEntity with the appropriate error code, message, and HTTP status code 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception) {
        return buildResponseEntity(ErrorCodes.UNEXPECTED_ERROR.toString(), "Une erreur inattendue s'est produite : " + exception.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
