package com.iqmsoft.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String helloWorld() {
        return "Hello World From Spring Boot Reactor";
    }
}
