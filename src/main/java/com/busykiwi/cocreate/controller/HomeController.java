package com.busykiwi.cocreate.controller;

import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("home")
    public String home() {
        return "Let's Create and Build Together";
    }
}
