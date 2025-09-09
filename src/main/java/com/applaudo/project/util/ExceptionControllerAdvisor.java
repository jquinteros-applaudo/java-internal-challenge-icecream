package com.applaudo.project.util;

import com.applaudo.project.model.APIError;
import com.applaudo.project.model.exceptions.IceCreamDuplicatedException;
import com.applaudo.project.model.exceptions.IceCreamNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>
 * Example:
 * 
 * <pre>
 *     <code>@ExceptionHandler(NullPointerException.class)
 *     public ResponseEntity<APIError> handle(NullPointerException ex) {
 *         return null;
 *     }
 *     </code>
 * </pre>
 */
@ControllerAdvice
public class ExceptionControllerAdvisor {

    @ExceptionHandler(IceCreamNotFoundException.class)
    public ResponseEntity<APIError> handleIceCreamNotFound(IceCreamNotFoundException ex) {
        APIError error = APIError.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .description(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

     @ExceptionHandler(IceCreamDuplicatedException.class)
    public ResponseEntity<APIError> handleIceCreamDuplicated(IceCreamDuplicatedException ex) {
        APIError error = APIError.builder()
                .code(HttpStatus.CONFLICT.value())
                .description(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleValidationError(MethodArgumentNotValidException ex) {
        APIError error = APIError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .description(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
