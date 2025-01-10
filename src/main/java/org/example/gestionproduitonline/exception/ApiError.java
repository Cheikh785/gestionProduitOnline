package org.example.gestionproduitonline.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

import java.util.List;

public class ApiError {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String code;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ApiFieldError> fields;

    public static Builder builder() {
        return new Builder();
    }

    private ApiError(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
    }

    public static class Builder {
        private String code;
        private String message;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }

    public ApiError() {
    }

    public ApiError(String code, String message, List<ApiFieldError> fields) {
        this.code = code;
        this.message = message;
        this.fields = fields;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ApiFieldError> getFields() {
        return fields;
    }

    public void setFields(List<ApiFieldError> fields) {
        this.fields = fields;
    }

}


