package com.aquent.crudapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.entity.Person;
import com.aquent.crudapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for handling basic person management operations.
 */
@Controller
@RequestMapping("person")
public class PersonController {

    private static final String COMMAND_DELETE = "Delete";

    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Renders the listing page.
     *
     * @return list view populated with the current list of people
     */
    @GetMapping(value = "list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("person/list");
        mav.addObject("persons", personService.list());
        return mav;
    }

    /**
     * Renders an empty form used to create a new person record.
     *
     * @return create view populated with an empty person
     */
    @GetMapping(value = "create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("person/create");
        mav.addObject("person", new Person());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves a new person.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param person populated form bean for the person
     * @return redirect, or create view with errors
     */
    @PostMapping(value = "create")
    public ModelAndView create(PersonDTO person) {
        List<String> errors = personService.validate(person);
        if (errors.isEmpty()) {
            personService.create(person);
            return new ModelAndView("redirect:/person/list");
        } else {
            ModelAndView mav = new ModelAndView("person/create");
            mav.addObject("person", person);
            mav.addObject("errors", errors);
            return mav;
        }
    }

    /**
     * Renders an edit form for an existing person record.
     *
     * @param personId the ID of the person to edit
     * @return edit view populated from the person record
     */
    @GetMapping(value = "edit/{personId}")
    public ModelAndView edit(@PathVariable Long personId) {
        ModelAndView mav = new ModelAndView("person/edit");
        mav.addObject("person", personService.get(personId));
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves an edited person.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param person populated form bean for the person
     * @return redirect, or edit view with errors
     */
    @PostMapping(value = "edit")
    public ModelAndView edit(PersonDTO person) {
        List<String> errors = personService.validate(person);
        if (errors.isEmpty()) {
            personService.update(person);
            return new ModelAndView("redirect:/person/list");
        } else {
            ModelAndView mav = new ModelAndView("person/edit");
            mav.addObject("person", person);
            mav.addObject("errors", errors);
            return mav;
        }
    }

    /**
     * Renders the deletion confirmation page.
     *
     * @param personId the ID of the person to be deleted
     * @return delete view populated from the person record
     */
    @GetMapping(value = "delete/{personId}")
    public ModelAndView delete(@PathVariable Long personId) {
        ModelAndView mav = new ModelAndView("person/delete");
        mav.addObject("person", personService.get(personId));
        return mav;
    }

    /**
     * Handles person deletion or cancellation, redirecting to the listing page in either case.
     *
     * @param command the command field from the form
     * @param personId the ID of the person to be deleted
     * @return redirect to the listing page
     */
    @PostMapping(value = "delete")
    public String delete(@RequestParam String command, @RequestParam Long personId) {
        if (COMMAND_DELETE.equals(command)) {
            personService.delete(personId);
        }
        return "redirect:/person/list";
    }
}
