package aces.ark_encoded_listener.error_handler;

import lombok.Data;

@Data
public class FieldError {
    private String field;
    private String code;
    private String message;
}
