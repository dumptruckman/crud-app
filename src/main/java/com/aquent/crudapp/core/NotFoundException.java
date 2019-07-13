package com.aquent.crudapp.core;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class clazz, Object identifier) {
        super(String.format("%s not found with id: %s", clazz.getCanonicalName(), identifier.toString()));
    }
}
