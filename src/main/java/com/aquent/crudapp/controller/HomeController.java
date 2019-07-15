package com.aquent.crudapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Simple controller to redirect to the client listing.  In the future, we could
 * add other landing page behavior here if we were writing a real application.
 */
@Controller
public class HomeController {
    /**
     * Redirect to the client list.
     * In a real application this could be our landing or login page.
     *
     * @return redirect to the client list
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/client/list";
    }
}
