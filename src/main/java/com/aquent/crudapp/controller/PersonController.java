package com.aquent.crudapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.aquent.crudapp.domain.Address;
import com.aquent.crudapp.domain.AddressType;
import com.aquent.crudapp.domain.Client;
import com.aquent.crudapp.service.AddressService;
import com.aquent.crudapp.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aquent.crudapp.domain.Person;

/**
 * Controller for handling basic person management operations.
 */
@Controller
@RequestMapping("person")
public class PersonController {

    public static final String COMMAND_DELETE = "Delete";

    @Inject private PersonService personService;
    @Inject private AddressService addressService;

    /**
     * Renders the listing page.
     *
     * @return list view populated with the current list of people
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("person/list");
        mav.addObject("persons", personService.listPeople());
        return mav;
    }

    /**
     * Renders an empty form used to create a new person record.
     *
     * @return create view populated with an empty person
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("person/create");
        mav.addObject("person", new Person());
        Address address = new Address();
        address.setAddressType(AddressType.PERSONAL);
        mav.addObject("address", address);
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves a new person.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param person populated form bean for the person
     * @param address populated form bean for the address
     * @return redirect, or create view with errors
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(Person person, Address address) {
        person.setAddress(address);
        List<String> errors = personService.validatePerson(person);
        errors.addAll(addressService.validateAddress(address));
        if (errors.isEmpty()) {
            personService.createPerson(person);
            return new ModelAndView("redirect:/person/list");
        } else {
            return createErrorMav("person/create", person, address, errors);
        }
    }

    private ModelAndView createErrorMav(String path, Person person, Address address, List<String> errors) {
        ModelAndView mav = new ModelAndView(path);
        mav.addObject("person", person);
        mav.addObject("address", address);
        mav.addObject("errors", errors);
        return mav;
    }

    /**
     * Renders an edit form for an existing person record.
     *
     * @param personId the ID of the person to edit
     * @return edit view populated from the person record
     */
    @RequestMapping(value = "edit/{personId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer personId) {
        ModelAndView mav = new ModelAndView("person/edit");
        Person person = personService.readPerson(personId);
        mav.addObject("person", person);
        mav.addObject("address", person.getAddress());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves an edited person.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param person populated form bean for the person
     * @param address populated for bean for the address
     * @return redirect, or edit view with errors
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView edit(Person person, Address address) {
        person.setAddress(address);
        List<String> errors = personService.validatePerson(person);
        errors.addAll(addressService.validateAddress(address));
        if (errors.isEmpty()) {
            personService.updatePerson(person);
            return new ModelAndView("redirect:/person/list");
        } else {
            return createErrorMav("person/edit", person, address, errors);
        }
    }

    /**
     * Renders the deletion confirmation page.
     *
     * @param personId the ID of the person to be deleted
     * @return delete view populated from the person record
     */
    @RequestMapping(value = "delete/{personId}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer personId) {
        ModelAndView mav = new ModelAndView("person/delete");
        Person person = personService.readPerson(personId);
        mav.addObject("person", person);
        mav.addObject("address", person.getAddress());
        return mav;
    }

    /**
     * Handles person deletion or cancellation, redirecting to the listing page in either case.
     *
     * @param command the command field from the form
     * @param personId the ID of the person to be deleted
     * @param addressId the ID of the address to be deleted
     * @return redirect to the listing page
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam String command, @RequestParam Integer personId, @RequestParam Integer addressId) {
        if (COMMAND_DELETE.equals(command)) {
            // TODO figure out a way to make these transactional
            personService.deletePerson(personId);
            addressService.deleteAddress(addressId);
        }
        return "redirect:/person/list";
    }
}
