package aces.ark_encoded_listener.error_handler;

import lombok.Data;

import java.util.List;

@Data
public class ValidationError {
    private String code;
    private String message;
    private List<FieldError> fieldErrors;
}
