package com.iqmsoft.service;

import org.springframework.stereotype.Service;

import com.iqmsoft.model.Hello;
import com.iqmsoft.model.HelloEvent;
import com.iqmsoft.repository.HelloRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class HelloService {

    private HelloRepository greetingRepository;

    public HelloService(HelloRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Flux<Hello> findAll() {
        return greetingRepository.findAll();
    }

    public Mono<Hello> findById(final String id) {
        return greetingRepository.findById(id);
    }

    public Flux<HelloEvent> streamById(final String id) {
        return findById(id).flatMapMany(greeting -> {
            final Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
            final Stream<HelloEvent> greetingEventsStream = Stream.generate(() -> new HelloEvent(greeting, new Date()));
            final Flux<HelloEvent> events = Flux.fromStream(greetingEventsStream);
            return Flux.zip(interval, events).map(Tuple2::getT2);
        });
    }
}
