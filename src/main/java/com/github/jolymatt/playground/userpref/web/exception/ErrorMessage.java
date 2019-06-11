package com.github.jolymatt.playground.userpref.web.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The ErrorMessage class
 *
 * @author Joly Mathew
 */
public class ErrorMessage {

    @JsonProperty("status")
    private int status;
    @JsonProperty("url")
    private String url;
    @JsonProperty("errors")
    private List<String> errors;

    public ErrorMessage() {

    }


    public ErrorMessage(String url, List<String> errors) {
        this.url = url;
        this.errors = errors;
    }

    public ErrorMessage(String url, String error) {
        this(url, Collections.singletonList(error));
    }

    public ErrorMessage(String url, String... errors) {
        this(url, Arrays.asList(errors));
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getUrl() {
        return url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
