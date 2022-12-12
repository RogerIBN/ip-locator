package com.accenture.modern_cloud_engineering.ip_locator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/example")
    public Object example() {
        String url = "https://api.ipify.org/?format=json";

        RestTemplate restTemplate = new RestTemplate();

        Object result = restTemplate.getForObject(url, Object.class);

        return result;

    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
