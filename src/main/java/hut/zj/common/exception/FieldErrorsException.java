package hut.zj.common.exception;

import org.springframework.validation.FieldError;

import java.util.List;

public class FieldErrorsException extends RuntimeException {
    private List<FieldError> fieldErrors;

    public FieldErrorsException(String msg, List<FieldError> fieldErrors) {
        super(msg);
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
