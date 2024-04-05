package com.uvocab.api.handler;

import static org.springframework.http.HttpStatus.*;

import com.uvocab.api.exception.NotFoundException;
import com.uvocab.api.exception.UsernameNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<String> notFound(NotFoundException notFoundException) {
    return new ResponseEntity<>(notFoundException.getMessage(), NOT_FOUND);
  }

  @ExceptionHandler({UsernameNotFoundException.class})
  public ResponseEntity<String> userNotFound(UsernameNotFoundException usernameNotFoundException) {
    return new ResponseEntity<>(usernameNotFoundException.getMessage(), NOT_FOUND);
  }
}
