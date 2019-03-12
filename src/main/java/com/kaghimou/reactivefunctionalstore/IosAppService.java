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


    public Flux<IosApp> streamIosApps(){

        Flux<IosApp> iosApps = this.iosAppRepository.findAll();
        return Flux.zip(Flux.interval(Duration.ofSeconds(1)), iosApps).map(Tuple2::getT2);
    }


    Flux<IosApp> getAllApp(){
        return this.iosAppRepository.findAll();
    }

}
