package com.kaghimou.reactivefunctionalstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class IosAppRouter {

    @Bean
    RouterFunction<ServerResponse> routes (IosAppService iosAppService){

        return route(GET("/iosApp"), request -> ok()
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(iosAppService.streamSecondes(), Long.class))


                .andRoute(GET("/allApp"), request -> ok()
                        .body(iosAppService.getAllApp(), IosApp.class))


                .andRoute(GET("/seconds"), request -> ok()
                        .body(iosAppService.streamSecondes(), Long.class));

    }


}
