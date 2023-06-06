package com.ua.rosella.exceptions;

import java.util.Set;

public class ObjectNotValidException extends RuntimeException {
    private final Set<String> errorMessages;

    public ObjectNotValidException(Set<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
    public Set<String> getErrorMessages() {
        return errorMessages;
    }
}
