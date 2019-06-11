package com.github.jolymatt.playground.userpref.web.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

/**
 * The ServiceExceptionHandler class
 *
 * @author Joly Mathew
 */
@ControllerAdvice
public class ServiceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestServiceException.class)
    public @ResponseBody
    ErrorMessage handleRestServiceExceptionn(HttpServletRequest req, RestServiceException ex) {
        return new ErrorMessage(req.getRequestURL().toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public @ResponseBody
    ErrorMessage handleBindExceptionException(HttpServletRequest req, BindException ex) {
        return new ErrorMessage(req.getRequestURL().toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.class)
    public @ResponseBody
    ErrorMessage handleHttpClientErrorException(HttpServletRequest req, HttpClientErrorException ex) {
        return new ErrorMessage(req.getRequestURL().toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(RestClientException.class)
    public @ResponseBody
    ErrorMessage handleRestClientException(HttpServletRequest req, RestClientException ex) {
        return new ErrorMessage(req.getRequestURL().toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody
    ErrorMessage handleRunTimeException(HttpServletRequest req, RuntimeException ex) {
        return new ErrorMessage(req.getRequestURL().toString(), ex.getMessage());
    }
}
