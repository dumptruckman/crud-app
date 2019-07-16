package com.aquent.crudapp.service;

import com.aquent.crudapp.dto.ClientDTO;
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
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    private ClientDTO newClient;

    @Before
    public void setup() {
        ClientDTO client = new ClientDTO();
        client.setName("NewCorp");
        client.setStreetAddress("123 Main St.");
        client.setCity("Somewhere");
        client.setState("NC");
        client.setZipCode("12345");
        client.setWebsiteUrl("http://www.newcorp.services");
        client.setPhoneNumber("8888675309");

        newClient = client;
    }

    @Test
    public void testList() {
        // Get clients (only one in DB), make sure not null and size = 1
        Collection<ClientDTO> clients = clientService.list();
        assertThat(clients, is(notNullValue()));
        assertThat(clients, hasSize(1));
    }

    @Test
    public void testGet() {
        ClientDTO client = clientService.get(1L);
        assertThat(client, is(notNullValue()));

        // Make sure ID returns correctly
        assertThat(client.getId(), is(1L));

        // Fill test required fields - just need to not be null
        assertThat(client.getName(), is(notNullValue()));
        assertThat(client.getStreetAddress(), is(notNullValue()));
        assertThat(client.getCity(), is(notNullValue()));
        assertThat(client.getState(), is(notNullValue()));
        assertThat(client.getZipCode(), is(notNullValue()));
        assertThat(client.getWebsiteUrl(), is(notNullValue()));
        assertThat(client.getPhoneNumber(), is(notNullValue()));
    }

    @Test
    public void testUpdate() {
        ClientDTO client = clientService.get(1L);
        client.setName("Whizbang LLC");
        clientService.update(client);
        client = clientService.get(1L);

        assertThat(client.getName(), is("Whizbang LLC"));
    }

    @Test
    public void testCreate() {
        ClientDTO client = clientService.create(newClient);
        assertThat(client.getId(), is(2L));

        client = clientService.get(2L);
        assertThat(client, is(notNullValue()));
    }

    @Test
    public void testDelete() {
        clientService.delete(1L);

        try {
            clientService.get(1L);
            Assert.fail("This should not have succeeded.");
        } catch (NotFoundException nfe) {
            assertThat(nfe.getMessage(), endsWith("1"));
        } catch (Throwable t) {
            Assert.fail("Wrong type of exception caught.");
        }
    }
}
