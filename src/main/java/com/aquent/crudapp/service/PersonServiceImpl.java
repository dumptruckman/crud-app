package com.aquent.crudapp.service;

import com.aquent.crudapp.core.NotFoundException;
import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.entity.Person;
import com.aquent.crudapp.mapping.PersonMapping;
import com.aquent.crudapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class PersonServiceImpl implements PersonService {

    @Autowired private PersonRepository repo;
    @Autowired private Validator validator;
    @Autowired private PersonMapping map;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PersonDTO get(Long id) {
        Person entity = repo.findById(id).orElseThrow(() -> new NotFoundException(this.getClass(), id));

        return map.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PersonDTO> list() {
        List<PersonDTO> result = new ArrayList<>();
        repo.findAll().forEach((item) -> {
            PersonDTO dto = map.fromEntity(item);
            result.add(dto);
        });

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PersonDTO create(PersonDTO person) {
        Person entity = map.toEntity(person, new Person());
        entity = repo.save(entity);

        return map.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PersonDTO update(PersonDTO person) {
        Person entity = repo.findById(person.getId()).orElseThrow(() -> new NotFoundException(this.getClass(), person.getId()));
        entity = map.toEntity(person, entity);
        entity = repo.save(entity);

        return map.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        Person entity = repo.findById(id).orElseThrow(() -> new NotFoundException(this.getClass(), id));
        repo.delete(entity);
    }

    @Override
    public List<String> validate(PersonDTO person) {
        Person entity = map.toEntity(person, new Person());
        Set<ConstraintViolation<Person>> violations = validator.validate(entity);
        List<String> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<Person> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
