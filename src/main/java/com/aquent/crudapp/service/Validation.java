package com.aquent.crudapp.service;

import java.util.List;

/**
 * Describes methods that should
 * be implemented by a service that
 * validates its associated data
 *
 * @param <T> is the type of data that needs
 *           to be validated by the service
 */
public interface Validation<T> {
    List<String> validate(T t);
}
