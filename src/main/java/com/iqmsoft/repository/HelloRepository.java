package com.iqmsoft.repository;

import org.springframework.stereotype.Component;

import com.iqmsoft.model.Hello;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class HelloRepository {

    private static final Hello FIRST_GREETING = new Hello("First Welcome");
    private static final Hello SECOND_GREETING = new Hello("Second Welcome");
    private static final Hello THIRDY_GREETING = new Hello("Thirdy Welcome");

    public static final Map<String, Hello> greetingsById = new LinkedHashMap<>();

    static {
        greetingsById.put("1", FIRST_GREETING);
        greetingsById.put("2", SECOND_GREETING);
        greetingsById.put("3", THIRDY_GREETING);
    }

    public Flux<Hello> findAll() {
        return Flux.just(FIRST_GREETING, SECOND_GREETING, THIRDY_GREETING);
    }

    public Mono<Hello> findById(final String id) {
        return Mono.just(greetingsById.get(id));
    }
}
