package com.kaghimou.reactivefunctionalstore;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IosAppRepository extends ReactiveMongoRepository <IosApp, Long>{

}
