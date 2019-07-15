package com.aquent.crudapp.service;

import java.util.Collection;
import java.util.List;

public interface CrudService<T> {

   Collection<T> list();

    T create(T t);

    T get(Long id);

    T update(T t);

    void delete(Long id);

}
