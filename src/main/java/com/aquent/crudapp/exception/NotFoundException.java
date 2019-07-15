package com.aquent.crudapp.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class clazz, Object identifier) {
        super(String.format("%s not found with id: %s", clazz.getCanonicalName(), identifier.toString()));
    }
}
