package org.example.gestionproduitonline.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiFieldError {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    public ApiFieldError() {}

    public ApiFieldError(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
