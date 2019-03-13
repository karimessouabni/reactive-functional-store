package com.kaghimou.reactivefunctionalstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveFunctionalStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveFunctionalStoreApplication.class, args);
	}


	@Bean
	CommandLineRunner OnStartPopulateDatabase( IosAppRepository iosAppRepository){
		return args ->
		iosAppRepository.deleteAll().subscribe(null, null, () ->

                Stream.of("Angry Birds, Facebook, Instagram, Twitter, Slack, Google, Gmail".split(", ")).map(
						appName -> new IosApp(UUID.randomUUID().toString(), appName, new Random().nextInt(6)))
					.forEach(iosApp -> iosAppRepository.save(iosApp).subscribe(System.out::println)));

	}


}
