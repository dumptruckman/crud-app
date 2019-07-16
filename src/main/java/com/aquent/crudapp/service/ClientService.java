package com.aquent.crudapp.service;

import com.aquent.crudapp.dto.ClientDTO;

/**
 * Stub for client service
 *
 * Implements CrudService for standard CRUD access methods
 * Implements Validation as stub to show that this implements object constraint validation
 */
public interface ClientService extends CrudService<ClientDTO>, Validation<ClientDTO> {
}
