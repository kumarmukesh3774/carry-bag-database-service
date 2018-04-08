package com.project.os.carrybagdatabaseservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.os.carrybagdatabaseservice.carrybag.CarryBag;

public interface CarryBagRepository extends MongoRepository<CarryBag, String> {

}
