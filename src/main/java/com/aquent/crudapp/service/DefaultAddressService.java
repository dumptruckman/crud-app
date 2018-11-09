package com.aquent.crudapp.service;

import com.aquent.crudapp.data.dao.AddressDao;
import com.aquent.crudapp.data.dao.PersonDao;
import com.aquent.crudapp.domain.Address;
import com.aquent.crudapp.domain.Person;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of {@link AddressService}.
 */
public class DefaultAddressService implements AddressService {

    private AddressDao addressDao;
    private Validator validator;

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteAddress(Integer id) {
        addressDao.deleteAddress(id);
    }

    @Override
    public List<String> validateAddress(Address address) {
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        List<String> errors = new ArrayList<String>(violations.size());
        for (ConstraintViolation<Address> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
