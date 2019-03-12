package com.kaghimou.reactivefunctionalstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Arrays;
import java.util.Random;

import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class ReactiveFunctionalStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveFunctionalStoreApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes (IosAppService iosAppService){

		return route(RequestPredicates.GET("/iosApp"),
				request -> ok()
						.contentType(MediaType.TEXT_EVENT_STREAM)
						.body(iosAppService.streamIosApps(), IosApp.class))
                .andRoute(RequestPredicates.GET("/allApp"),
                        request -> ok()
                                .body(iosAppService.getAllApp(), IosApp.class));
	}

	@Bean
	CommandLineRunner OnStartPopulateDatabase( IosAppRepository iosAppRepository){
		return args ->
		iosAppRepository.deleteAll().subscribe(null, null, () ->

			Stream.of("Angry Birds, Facebook, instagram, twitter, Slack, Google, Gmail".split(", ")).map(
					appName -> new IosApp(UUID.randomUUID().toString(), appName, new Random().nextInt(1000000)))
					.forEach(iosApp -> iosAppRepository.save(iosApp).subscribe(System.out::println)));

	}




}
