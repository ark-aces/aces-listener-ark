package aces.ark_encoded_listener.error_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Error handleNotFoundException(NotFoundException notFoundException) {
        log.warn("Not found exception thrown", notFoundException);

        Error error = new Error();
        error.setCode(notFoundException.getCode());
        error.setMessage(notFoundException.getMessage());

        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ValidationError handleValidationError(ValidationException validationException) {
        log.warn("Validation exception thrown", validationException);

        ValidationError validationError = new ValidationError();
        validationError.setCode(validationException.getCode());
        validationError.setMessage(validationException.getMessage());
        validationError.setFieldErrors(validationException.getFieldErrors());

        return validationError;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Error handleException(Exception exception) {
        log.error("Uncaught exception thrown", exception);

        Error error = new Error();
        error.setCode("server_error");
        error.setMessage("An unexpected error has occurred.");

        return error;
    }

}
