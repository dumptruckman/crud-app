package com.aquent.crudapp.core;

import java.util.List;

public interface Validated<T> {
    List<String> validate(T t);
}
