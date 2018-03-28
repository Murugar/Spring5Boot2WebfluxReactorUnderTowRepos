package com.iqmsoft.http;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.iqmsoft.model.Hello;
import com.iqmsoft.model.HelloEvent;
import com.iqmsoft.service.HelloService;

import reactor.core.publisher.Mono;

@Component
public class WelcomeRouteHandler {

    private final HelloService greetingService;

    public WelcomeRouteHandler(HelloService greetingService) {
         this.greetingService = greetingService;
    }

    public Mono<ServerResponse> findAll(final ServerRequest serverRequest) {
         return ServerResponse.ok()
                .body(greetingService.findAll(), Hello.class)
                .doOnError(throwable -> new IllegalStateException("Invalid Greeting"));
    }

    public Mono<ServerResponse> findById(final ServerRequest serverRequest) {
         final String id = serverRequest.pathVariable("id");
         return ServerResponse.ok()
               .body(greetingService.findById(id), Hello.class)
               .doOnError(throwable -> new IllegalStateException("Invalid Greeting"));
    }

    public Mono<ServerResponse> streams(final ServerRequest serverRequest) {
         final String id = serverRequest.pathVariable("id");
         return ServerResponse.ok()
               .contentType(MediaType.TEXT_EVENT_STREAM)
               .body(greetingService.streamById(id), HelloEvent.class)
               .doOnError(throwable -> new IllegalStateException("Invalid Greeting"));
    }
}