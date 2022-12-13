package com.accenture.modern_cloud_engineering.ip_locator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Home page
     * 
     * @return index.html
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

}
