package com.kaghimou.reactivefunctionalstore;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
public class IosAppService {


    private final IosAppRepository iosAppRepository;

    IosAppService(IosAppRepository iosAppRepository){
        this.iosAppRepository = iosAppRepository;
    }


     Flux<IosApp> streamIosApps(){
         return Flux.zip(Flux.interval(Duration.ofSeconds(1)), this.iosAppRepository.findAll())
                 .map(tuple2 -> {
                     System.out.println(Thread.currentThread().getName());
                     return tuple2.getT2();
                 });
    }


    Flux<IosApp> getAllApp(){
        System.out.println(Thread.currentThread().getName());

        return this.iosAppRepository.findAll();
    }


    Flux<Long> getElementFromFlux() {

        //this.streamIosApps().subscribe(); uncomment this to run both functions in parallel mode

        return Flux.interval(Duration.of(1, ChronoUnit.SECONDS))
                .map(value -> {
                    System.out.println(Thread.currentThread().getName());
                    return value * 10;
                });

    }


}
