package com.fujitsu.cloudlab.commons.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiErrorHandler{

  @ExceptionHandler(java.util.NoSuchElementException.class)
  public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
    ErrorResponses errorResponses = new ErrorResponses();
    errorResponses.setCode("FAI-ORD-4004");
    errorResponses.setMessage("ORDER_NOT_FOUND");
    errorResponses.setDeveloperMessage(
        ex.getMessage()
            + "::"
            + ex.getLocalizedMessage()
            + "::"
            + ExceptionUtils.getStackTrace(ex));
    return new ResponseEntity<Object>(errorResponses, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<Object> handleApiExceptions(ApiException ex) {
    ErrorResponses errorResponses = new ErrorResponses();
    errorResponses.setCode(ex.getCode());
    errorResponses.setMessage(ex.getMessage());
    errorResponses.setDeveloperMessage(
        ex.getDeveloperMessage()          );
    return new ResponseEntity<Object>(
        errorResponses, new HttpHeaders(), HttpStatus.MULTI_STATUS);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllOtherExceptions(Exception ex, WebRequest request) {
    ErrorResponses errorResponses = new ErrorResponses();
    errorResponses.setCode(ex.getMessage());
    errorResponses.setMessage(ex.getMessage());
    errorResponses.setDeveloperMessage(
        ex.getMessage()
            + "::"
            + ex.getLocalizedMessage()
            + "::"
            + ExceptionUtils.getStackTrace(ex));
    return new ResponseEntity<Object>(
        errorResponses, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
