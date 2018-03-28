package com.iqmsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.iqmsoft.http.WelcomeRouteHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(WelcomeRouteHandler routeHandler) {
        return nest(path("/welcome"),
                route(RequestPredicates.GET(""), routeHandler::findAll)
                .andRoute(RequestPredicates.GET("/{id}"), routeHandler::findById)
                .andRoute(RequestPredicates.GET("/{id}/stream"), routeHandler::streams)
        );
    }

}