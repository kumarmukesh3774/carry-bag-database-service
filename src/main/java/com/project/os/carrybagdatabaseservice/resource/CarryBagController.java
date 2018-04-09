package com.project.os.carrybagdatabaseservice.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.os.carrybagdatabaseservice.carrybag.CarryBag;
import com.project.os.carrybagdatabaseservice.repository.CarryBagRepository;
@Component
@RestController
public class CarryBagController {
	@Autowired
	private CarryBagRepository carryBagRepository;
	
	
	@GetMapping("/bag1")
	public String greet() {

		return "Hello";
	}
	@HystrixCommand(fallbackMethod="fallbackGetAllBag")
	@GetMapping(value="/bag",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CarryBag> getAll() {

		return carryBagRepository.findAll();
	}
	public List<CarryBag> fallbackGetAllBag() {
		List <CarryBag> emptyList=new ArrayList<CarryBag>();
		emptyList.add(new CarryBag("default","default","default","default",
				0l,0l,"default","default"));
		return emptyList ;
	}
	
	@PostMapping("/bag2")
	public void addToCarryBag(@RequestBody CarryBag entity) {
		carryBagRepository.save(entity);

	}
	@DeleteMapping("/bag/offer-id/{id}")
	public void deleteById(@PathVariable String id) {
		carryBagRepository.deleteById(id);

	}
	
	
}
