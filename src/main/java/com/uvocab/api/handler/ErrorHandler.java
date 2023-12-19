package com.uvocab.api.handler;

import static org.springframework.http.HttpStatus.*;

import com.uvocab.api.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {
  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler({EmptyResultDataAccessException.class, NotFoundException.class})
  public void notFound() {}
}
