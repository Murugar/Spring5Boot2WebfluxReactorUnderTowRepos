package com.iqmsoft.http;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqmsoft.model.Hello;
import com.iqmsoft.model.HelloEvent;
import com.iqmsoft.service.HelloService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class WelcomeController {

    private HelloService greetingService;

    public WelcomeController(HelloService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public Flux<Hello> findAll() {
        return greetingService.findAll();
    }

    @RequestMapping(value = "/welcome/{id}", method = RequestMethod.GET)
    public Mono<Hello> findBydId(@PathVariable String id) {
        return greetingService.findById(id);
    }

    @RequestMapping(value = "/welcome/{id}/stream", method = RequestMethod.GET)
    public Flux<HelloEvent> streamById(@PathVariable String id) {
        return greetingService.streamById(id);
    }
}
