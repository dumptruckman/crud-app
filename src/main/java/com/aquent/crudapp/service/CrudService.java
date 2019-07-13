package com.aquent.crudapp.service;

import com.aquent.crudapp.core.NotFoundException;

import java.util.List;

public interface CrudService<T> {

   List<T> list();

    T create(T t);

    T get(Long id);

    T update(T t);

    void delete(Long id);

}
