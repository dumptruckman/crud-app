package com.aquent.crudapp.service;

import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    private PersonDTO newPerson;

    @Before
    public void setup() {
        PersonDTO person = new PersonDTO();
        person.setFirstName("Martin");
        person.setLastName("Freeman");
        person.setStreetAddress("123 Main St.");
        person.setCity("Somewhere");
        person.setState("NC");
        person.setZipCode("12345");
        person.setEmailAddress("person@gmail.com");

        newPerson = person;
    }

    @Test
    public void testList() {
        // Get persons (only one in DB), make sure not null and size = 1
        Collection<PersonDTO> persons = personService.list();
        assertThat(persons, is(notNullValue()));
        assertThat(persons, hasSize(2));
    }

    @Test
    public void testGet() {
        PersonDTO person = personService.get(1L);
        assertThat(person, is(notNullValue()));

        // Make sure ID returns correctly
        assertThat(person.getId(), is(1L));

        // Fill test required fields - just need to not be null
        assertThat(person.getFirstName(), is(notNullValue()));
        assertThat(person.getLastName(), is(notNullValue()));
        assertThat(person.getStreetAddress(), is(notNullValue()));
        assertThat(person.getCity(), is(notNullValue()));
        assertThat(person.getState(), is(notNullValue()));
        assertThat(person.getZipCode(), is(notNullValue()));
        assertThat(person.getEmailAddress(), is(notNullValue()));
    }

    @Test
    public void testUpdate() {
        PersonDTO person = personService.get(1L);
        person.setFirstName("Benedict");
        person.setLastName("Cumberbatch");
        personService.update(person);
        person = personService.get(1L);

        assertThat(person.getFirstName(), is("Benedict"));
        assertThat(person.getLastName(), is("Cumberbatch"));
    }

    @Test
    public void testCreate() {
        PersonDTO person = personService.create(newPerson);
        assertThat(person.getId(), is(3L));

        person = personService.get(2L);
        assertThat(person, is(notNullValue()));
    }

    @Test
    public void testDelete() {
        personService.delete(1L);

        try {
            personService.get(1L);
            Assert.fail("This should not have succeeded.");
        } catch (NotFoundException nfe) {
            assertThat(nfe.getMessage(), endsWith("1"));
        } catch (Throwable t) {
            Assert.fail("Wrong type of exception caught.");
        }
    } 
}
