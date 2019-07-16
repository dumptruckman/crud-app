package com.aquent.crudapp.exception;

/**
 * Exception class to handle when a resource is not found
 */
public class NotFoundException extends RuntimeException {

    /**
     * Returns a new NotFoundException
     *
     * @param clazz is the resource class that we were searching for an instance of
     * @param identifier is the identifier of the resource.
     */
    public NotFoundException(Class clazz, Object identifier) {
        super(String.format("%s not found with id: %s", clazz.getCanonicalName(), identifier.toString()));
    }
}
