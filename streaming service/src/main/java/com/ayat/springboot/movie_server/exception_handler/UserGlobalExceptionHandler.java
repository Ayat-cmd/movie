package com.ayat.springboot.movie_server.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<MCUException> handleException(NoSuchUserException exception) {
        MCUException data = new MCUException();
        data.setError(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MCUException> handleException(MovieIncorrectIdException exception) {
        MCUException data = new MCUException();
        data.setError(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MCUException> handleException(NoSuchComment exception) {
        MCUException data = new MCUException();
        data.setError(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MCUException> handleException(Exception exception) {
        MCUException data = new MCUException();
        data.setError(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
