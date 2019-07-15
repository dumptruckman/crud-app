package com.aquent.crudapp.controller;

import com.aquent.crudapp.dto.ClientDTO;
import com.aquent.crudapp.service.ClientService;
import com.aquent.crudapp.service.ContactService;
import com.aquent.crudapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("client")
public class ClientController {

    private static final String COMMAND_DELETE = "Delete";
    private static final String COMMAND_REMOVE = "Remove";

    @Autowired private PersonService personService;
    @Autowired private ClientService clientService;
    @Autowired private ContactService contactService;

    /**
     * Renders the listing page.
     *
     * @return list view populated with the current list of clients
     */
    @GetMapping(value = "list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("client/list");
        mav.addObject("clients", clientService.list());
        return mav;
    }

    /**
     * Views the client's information
     * @return view populated with the client's information
     */
    @GetMapping(value = "/{clientId}")
    public ModelAndView view(@PathVariable Long clientId) {
        ModelAndView mav = new ModelAndView("client/view");
        mav.addObject("client", clientService.get(clientId));
        return mav;
    }

    /**
     * Renders an empty form used to create a new client record.
     *
     * @return create view populated with an empty client
     */
    @GetMapping(value = "create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("client/create");
        mav.addObject("client", new ClientDTO());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves a new client.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param client populated form bean for the client
     * @return redirect, or create view with errors
     */
    @PostMapping(value = "create")
    public ModelAndView create(ClientDTO client) {
        List<String> errors = clientService.validate(client);
        if (errors.isEmpty()) {
            client = clientService.create(client);
            return new ModelAndView("redirect:/client/" + client.getId());
        } else {
            ModelAndView mav = new ModelAndView("client/create");
            mav.addObject("client", client);
            mav.addObject("people", personService.list());
            mav.addObject("errors", new ArrayList<String>());
            return mav;
        }
    }

    /**
     * Renders an edit form for an existing client record.
     *
     * @param clientId the ID of the client to edit
     * @return edit view populated from the client record
     */
    @GetMapping(value = "edit/{clientId}")
    public ModelAndView edit(@PathVariable Long clientId) {
        ModelAndView mav = new ModelAndView("client/edit");
        mav.addObject("client", clientService.get(clientId));
        mav.addObject("people", personService.list());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves an edited client.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param client populated form bean for the client
     * @return redirect, or edit view with errors
     */
    @PostMapping(value = "edit")
    public ModelAndView edit(ClientDTO client) {
        List<String> errors = clientService.validate(client);
        if (errors.isEmpty()) {
            clientService.update(client);
            return new ModelAndView("redirect:/client/" + client.getId());
        } else {
            ModelAndView mav = new ModelAndView("client/edit");
            mav.addObject("person", client);
            mav.addObject("errors", errors);
            return mav;
        }
    }

    /**
     * Renders the deletion confirmation page.
     *
     * @param clientId the ID of the client to be deleted
     * @return delete view populated from the client record
     */
    @GetMapping(value = "delete/{clientId}")
    public ModelAndView delete(@PathVariable Long clientId) {
        ModelAndView mav = new ModelAndView("client/delete");
        mav.addObject("client", clientService.get(clientId));
        return mav;
    }

    /**
     * Handles client deletion or cancellation, redirecting to the listing page in either case.
     *
     * @param command the command field from the form
     * @param clientId the ID of the client to be deleted
     * @return redirect to the listing page
     */
    @PostMapping(value = "delete")
    public ModelAndView delete(@RequestParam String command, @RequestParam Long clientId) {
        if (COMMAND_DELETE.equals(command)) {
            clientService.delete(clientId);
        }
        return new ModelAndView("redirect:/client/list");
    }

    @GetMapping(value = "/add/{clientId}")
    public ModelAndView add(@PathVariable Long clientId) {
        ModelAndView mav = new ModelAndView("client/add");
        mav.addObject("client", clientService.get(clientId));
        mav.addObject("people", personService.allUnassigned());
        return mav;
    }

    @PostMapping(value = "/add")
    public ModelAndView add(@RequestParam Long clientId, @RequestParam Long personId) {
        contactService.add(clientId, personId);

        return new ModelAndView("redirect:/client/" + clientId);
    }

    @GetMapping(value = "/remove/{clientId}/{personId}")
    public ModelAndView remove(@PathVariable Long clientId, @PathVariable Long personId) {
        ModelAndView mav = new ModelAndView("client/remove");
        mav.addObject("client", clientService.get(clientId));
        mav.addObject("person", personService.get(personId));

        return mav;
    }

    @PostMapping(value="/remove")
    public ModelAndView remove(@RequestParam String command, @RequestParam Long clientId, @RequestParam Long personId) {
        if (COMMAND_REMOVE.equals(command)) {
            contactService.remove(personId);
        }
        return new ModelAndView("redirect:/client/" + clientId);
    }
}
