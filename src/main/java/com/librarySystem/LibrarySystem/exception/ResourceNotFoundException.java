package com.librarySystem.LibrarySystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException {

    private String resource;
    private HttpStatus status;

    public ResourceNotFoundException(String message, String resource){
        super(message);
        this.resource = resource;
        this.status = HttpStatus.NOT_FOUND;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


}
