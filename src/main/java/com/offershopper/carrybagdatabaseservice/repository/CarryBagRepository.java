package com.offershopper.carrybagdatabaseservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.offershopper.carrybagdatabaseservice.model.CarryBag;

public interface CarryBagRepository extends MongoRepository<CarryBag, String> {

}
