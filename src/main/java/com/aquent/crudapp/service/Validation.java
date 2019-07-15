package com.aquent.crudapp.service;

import java.util.List;

public interface Validation<T> {
    List<String> validate(T t);
}
