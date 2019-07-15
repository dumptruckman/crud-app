package com.aquent.crudapp.service;

import com.aquent.crudapp.exception.NotFoundException;
import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.entity.Person;
import com.aquent.crudapp.mapping.entity.PersonEntityMapping;
import com.aquent.crudapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Component
public class PersonServiceImpl implements PersonService {

    @Autowired private PersonRepository personRepository;
    @Autowired private Validator validator;
    @Autowired private PersonEntityMapping personMapping;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PersonDTO get(Long id) {
        Person entity = personRepository.findById(id).orElseThrow(() -> new NotFoundException(this.getClass(), id));

        return personMapping.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<PersonDTO> list() {
        Set<PersonDTO> result = new TreeSet<>();
        personRepository.findAll().forEach((item) -> {
            PersonDTO dto = personMapping.fromEntity(item);
            result.add(dto);
        });

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<PersonDTO> allUnassigned() {
        Set<PersonDTO> result = new TreeSet<>();
        personRepository.findByClientIsNull().forEach((entity) -> {
            PersonDTO dto = personMapping.fromEntity(entity);
            result.add(dto);
        });

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PersonDTO create(PersonDTO person) {
        Person entity = personMapping.toEntity(person, new Person());

        return personMapping.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PersonDTO update(PersonDTO person) {
        Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new NotFoundException(this.getClass(), person.getId()));
        entity = personMapping.toEntity(person, entity);

        return personMapping.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        Person entity = personRepository.findById(id).orElseThrow(() -> new NotFoundException(this.getClass(), id));
        personRepository.delete(entity);
    }

    @Override
    public List<String> validate(PersonDTO person) {
        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        List<String> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<PersonDTO> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
