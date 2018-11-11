package com.aquent.crudapp.controller;

import com.aquent.crudapp.domain.Address;
import com.aquent.crudapp.domain.AddressType;
import com.aquent.crudapp.domain.Client;
import com.aquent.crudapp.domain.Person;
import com.aquent.crudapp.service.AddressService;
import com.aquent.crudapp.service.ClientService;
import com.aquent.crudapp.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for handling basic client management operations.
 */
@Controller
@RequestMapping("client")
public class ClientController {

    public static final String COMMAND_DELETE = "Delete";

    @Inject private ClientService clientService;
    @Inject private AddressService addressService;
    @Inject private PersonService personService;

    /**
     * Renders the listing page.
     *
     * @return list view populated with the current list of client
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("client/list");
        mav.addObject("clients", clientService.listClients());
        return mav;
    }

    /**
     * Renders an empty form used to create a new client record.
     *
     * @return create view populated with an empty client
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        Address physicalAddress = new Address();
        physicalAddress.setAddressType(AddressType.PHYSICAL);
        Address mailingAddress = new Address();
        mailingAddress.setAddressType(AddressType.MAILING);

        ModelAndView mav = new ModelAndView("client/create");
        mav.addObject("client", new Client());
        mav.addObject("physicalAddress", physicalAddress);
        mav.addObject("mailingAddress", mailingAddress);
        mav.addObject("people", personService.listPeople());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves a new client.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param client populated form bean for the client
     * @param addressType the types of addresses to be created
     * @param streetAddress the street addresses of addresses to be created
     * @param city the cities of addresses to be created
     * @param state the states of addresses to be created
     * @param zipCode the zip codes of addresses to be created
     * @return redirect, or create view with errors
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    // TODO find a cleaner way to handle multiple addresses in a form.
    public ModelAndView create(Client client, AddressType[] addressType, String[] streetAddress, String[] city, String[] state, String[] zipCode, Integer[] personId) {
        Address physicalAddress = createAddress(null, addressType[0], streetAddress[0], city[0], state[0], zipCode[0]);
        Address mailingAddress = createAddress(null, addressType[1], streetAddress[1], city[1], state[1], zipCode[1]);
        client.setPhysicalAddress(physicalAddress);
        client.setMailingAddress(mailingAddress);

        client.setContacts(createContacts(client, personId));

        List<String> errors = clientService.validateClient(client);
        errors.addAll(addressService.validateAddress(physicalAddress));
        errors.addAll(addressService.validateAddress(mailingAddress));
        if (errors.isEmpty()) {
            clientService.createClient(client);
            return new ModelAndView("redirect:/client/list");
        } else {
            return createErrorMav("client/create", client, physicalAddress, mailingAddress, errors);
        }
    }

    private Address createAddress(Integer addressId, AddressType addressType, String streetAddress, String city, String state, String zipCode) {
        Address address = new Address();
        if (addressId != null) {
            address.setAddressId(addressId);
        }
        address.setAddressType(addressType);
        address.setStreetAddress(streetAddress);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        return address;
    }

    private List<Person> createContacts(Client client, Integer[] personId) {
        List<Person> contacts = new LinkedList<>();
        if (personId != null) {
            for (Integer id : personId) {
                Person p = new Person();
                p.setPersonId(id);
                p.setClient(client);
                contacts.add(p);
            }
        }
        return contacts;
    }

    private ModelAndView createErrorMav(String path, Client client, Address physicalAddress, Address mailingAddress, List<String> errors) {
        ModelAndView mav = new ModelAndView(path);
        mav.addObject("client", client);
        mav.addObject("physicalAddress", physicalAddress);
        mav.addObject("mailingAddress", mailingAddress);
        mav.addObject("people", personService.listPeople());
        mav.addObject("errors", errors);
        return mav;
    }

    /**
     * Renders an edit form for an existing client record.
     *
     * @param clientId the ID of the client to edit
     * @return edit view populated from the client record
     */
    @RequestMapping(value = "edit/{clientId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView("client/edit");
        Client client = clientService.readClient(clientId);
        mav.addObject("client", client);
        mav.addObject("physicalAddress", client.getPhysicalAddress());
        mav.addObject("mailingAddress", client.getMailingAddress());
        mav.addObject("people", personService.listPeople());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves an edited client.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param client populated form bean for the client
     * @param addressId the ids of the addresses to be edited
     * @param addressType the types of addresses to be edited
     * @param streetAddress the street addresses of addresses to be edited
     * @param city the cities of addresses to be edited
     * @param state the states of addresses to be edited
     * @param zipCode the zip codes of addresses to be edited
     * @return redirect, or edit view with errors
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    // TODO find a cleaner way to handle multiple addresses in a form.
    public ModelAndView edit(Client client, Integer[] addressId, AddressType[] addressType, String[] streetAddress, String[] city, String[] state, String[] zipCode, Integer[] personId) {
        Address physicalAddress = createAddress(addressId[0], addressType[0], streetAddress[0], city[0], state[0], zipCode[0]);
        Address mailingAddress = createAddress(addressId[1], addressType[1], streetAddress[1], city[1], state[1], zipCode[1]);
        client.setPhysicalAddress(physicalAddress);
        client.setMailingAddress(mailingAddress);

        client.setContacts(createContacts(client, personId));

        List<String> errors = clientService.validateClient(client);
        errors.addAll(addressService.validateAddress(physicalAddress));
        errors.addAll(addressService.validateAddress(mailingAddress));
        if (errors.isEmpty()) {
            clientService.updateClient(client);
            return new ModelAndView("redirect:/client/list");
        } else {
            return createErrorMav("client/edit", client, physicalAddress, mailingAddress, errors);
        }
    }

    /**
     * Renders the deletion confirmation page.
     *
     * @param clientId the ID of the client to be deleted
     * @return delete view populated from the client record
     */
    @RequestMapping(value = "delete/{clientId}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView("client/delete");
        Client client = clientService.readClient(clientId);
        mav.addObject("client", client);
        mav.addObject("physicalAddress", client.getPhysicalAddress());
        mav.addObject("mailingAddress", client.getMailingAddress());
        return mav;
    }

    /**
     * Handles client deletion or cancellation, redirecting to the listing page in either case.
     *
     * @param command the command field from the form
     * @param clientId the ID of the client to be deleted
     * @param physicalAddressId the ID of the physical address to be deleted
     * @param mailingAddressId the ID of the mailing address to be deleted
     * @return redirect to the listing page
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam String command, @RequestParam Integer clientId, @RequestParam Integer physicalAddressId, @RequestParam Integer mailingAddressId) {
        if (COMMAND_DELETE.equals(command)) {
            // TODO figure out a way to make these transactional
            clientService.deleteClient(clientId);
            addressService.deleteAddress(physicalAddressId);
            addressService.deleteAddress(mailingAddressId);
        }
        return "redirect:/client/list";
    }
}
