package com.kaghimou.reactivefunctionalstore;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

@Service
public class IosAppService {


    private final IosAppRepository iosAppRepository;

    IosAppService(IosAppRepository iosAppRepository){
        this.iosAppRepository = iosAppRepository;
    }


     Flux<IosApp> streamIosApps(){

        return Flux.zip(Flux.interval(Duration.ofSeconds(1)), this.iosAppRepository.findAll()).map(Tuple2::getT2);
    }


    Flux<IosApp> getAllApp(){
        return this.iosAppRepository.findAll();
    }

    Flux<Long> streamSecondes(){
        return Flux.interval(Duration.ofSeconds(1));

    }

}
