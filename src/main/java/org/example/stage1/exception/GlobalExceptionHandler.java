package org.example.stage1.exception;

import org.example.stage1.response.StandardResponse;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotExists.class)
    public ResponseEntity<StandardResponse> handleNotExists(NotExists ex, WebRequest request) {
        Map<String, String> error = Map.of("type", "Resource Not Found", "message", ex.getMessage());
        return new ResponseEntity<>(new StandardResponse("error", null, error), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<StandardResponse> handleAlreadyExists(AlreadyExists ex, WebRequest request) {
        Map<String, String> error = Map.of("type", "Conflict", "message", ex.getMessage());
        return new ResponseEntity<>(new StandardResponse("error", null, error), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StudentIdAndIdMismatch.class)
    public ResponseEntity<StandardResponse> handleIdMismatch(StudentIdAndIdMismatch ex, WebRequest request) {
        Map<String, String> error = Map.of("type", "ID Mismatch", "message", ex.getMessage());
        return new ResponseEntity<>(new StandardResponse("error", null, error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        Map<String, Object> error = Map.of("type", "Validation Failed", "fields", errors);
        return new ResponseEntity<>(new StandardResponse("error", null, error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleGeneric(Exception ex, WebRequest request) {
        Map<String, String> error = Map.of("type", "Internal Error", "message", ex.getMessage());
        return new ResponseEntity<>(new StandardResponse("error", null, error), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
