
package com.github.jolymatt.playground.userpref.web.exception;

/**
 * The RestServiceException class
 * @author Joly Mathew
 */
public class RestServiceException extends RuntimeException {

    public RestServiceException() {
        super();
    }

    public RestServiceException(String message) {
        super(message);
    }

    public RestServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
