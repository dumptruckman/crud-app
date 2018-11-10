package com.aquent.crudapp.service;

import com.aquent.crudapp.data.dao.AddressDao;
import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Address;
import com.aquent.crudapp.domain.Client;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of {@link ClientService}.
 */
public class DefaultClientService implements ClientService {

    private ClientDao clientDao;
    private AddressDao addressDao;
    private Validator validator;

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listClients() {
        return clientDao.listClients();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readClient(Integer id) {
        Client client = clientDao.readClient(id);
        Address physicalAddress = addressDao.readPhysicalAddressForClient(id);
        Address mailingAddress = addressDao.readMailingAddressForClient(id);
        client.setPhysicalAddress(physicalAddress);
        client.setMailingAddress(mailingAddress);
        return client;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClient(Client client) {
        Integer clientId = clientDao.createClient(client);
        client.setClientId(clientId);
        addressDao.createPhysicalAddressForClient(client);
        addressDao.createMailingAddressForClient(client);
        return clientId;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClient(Client client) {
        clientDao.updateClient(client);
        addressDao.updateAddress(client.getPhysicalAddress());
        addressDao.updateAddress(client.getMailingAddress());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteClient(Integer id) {
        clientDao.deleteClient(id);
    }

    @Override
    public List<String> validateClient(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        List<String> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<Client> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
