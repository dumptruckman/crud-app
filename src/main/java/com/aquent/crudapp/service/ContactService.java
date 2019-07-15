package com.aquent.crudapp.service;

public interface ContactService {
    void add(Long clientId, Long personId);
    void remove(Long personId);
}
