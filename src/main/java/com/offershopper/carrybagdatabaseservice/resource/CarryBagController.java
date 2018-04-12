package com.offershopper.carrybagdatabaseservice.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offershopper.carrybagdatabaseservice.MessageSender;
import com.offershopper.carrybagdatabaseservice.model.CarryBagBean;
import com.offershopper.carrybagdatabaseservice.repository.CarryBagRepository;
@Component
@RestController
public class CarryBagController {
	//Rabbitmq part not in the running condition
	//private Logger logger = LoggerFactory.getLogger(this.getClass());

	//using messege sender service to send message to rabbitmq
	//@Autowired
   //private MessageSender sendMessageToRabbit;
	
	
	@Autowired
	private CarryBagRepository carryBagRepository;
	
	/*
	@GetMapping("/bag1")
	public String greet() {

		return "Hello";
	}*/
	
// method to get the offers list
	@HystrixCommand(fallbackMethod="fallbackGetAllBag")
	@GetMapping(value="/bag/user-id/{userId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CarryBagBean> getAllByUserId(@PathVariable String userId) {
		//logger.info("{}", "hi");

		return carryBagRepository.findByUserId(userId);
	}
	
	//Fallback method for getting the offer from the list

	public List<CarryBagBean> fallbackGetAllByUserId(@PathVariable String userId) {
		List <CarryBagBean> emptyList=new ArrayList<CarryBagBean>();
		emptyList.add(new CarryBagBean("default","default","default","default",
				0l,0l,"default","default"));
		return emptyList ;
	}
	
	
/*	//a demo mapping to send msg
	@RequestMapping("/send")
	public String sendMsg(){
		String msg="sending";
		logger.info("Sending message...");
		sendMessageToRabbit.produceMsg(msg);
		return "Done";
	}
		
	//a demo mapping to send msg
	@RequestMapping("/sendd")
	public String sendMsgg(){
		String msg="sendingg";
		logger.info("Sending message...");
		sendMessageToRabbit.produceMsg(msg);
		return "Done";
}*/
/*	@GetMapping("/offers")
	public List<CarryBagBean> getofferslist()
	{
		return carryBagRepository.findAll();
	}*/
	
	
	
	
	@HystrixCommand(fallbackMethod="fallbackaddToCarryBag")
	@PostMapping("/bag/add")
	public ResponseEntity<Object>  addToCarryBag(@RequestBody CarryBagBean entity) throws Exception {
		
		if(carryBagRepository.existsById(entity.getOfferId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

			carryBagRepository.insert(entity);
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	//Fallback method for adding the offer to the list
	public ResponseEntity<Object>  fallbackaddToCarryBag(@RequestBody CarryBagBean entity) {
		
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
	
	
	
	/*@HystrixCommand(fallbackMethod="fallbackUpdateCarryBag")
	@PutMapping("/bag/update")
	public void  updateCarryBag(@RequestBody CarryBagBean entity) throws Exception {
		
		if(carryBagRepository.existsById(entity.getOfferId())) {
			carryBagRepository.save(entity);
		}

			

	}
	//Fallback method for adding the offer to the list
	public void  fallbackUpdateCarryBag(@RequestBody CarryBagBean entity) {
		
		
		
	}*/
	
	
	@HystrixCommand(fallbackMethod="fallbackUpdateCarryBag")
	 @PutMapping("/bag/update")
	   public ResponseEntity<Object> updateCarryBag(@RequestBody CarryBagBean entity)throws Exception  {
	        List<CarryBagBean> carryBagData = carryBagRepository.findAll();
	        ListIterator<CarryBagBean> listIterator =carryBagData.listIterator();
	        Integer flag = 0;
	        while(listIterator.hasNext()) {
	        	CarryBagBean carrybag = listIterator.next();
	            if((entity.getOfferId()).equals(carrybag.getOfferId())) {
	                if(entity.getOfferTitle()!=null)
	                	carrybag.setOfferTitle(entity.getOfferTitle());
	                if(entity.getOfferImage()!=null)
	                	carrybag.setOfferImage(entity.getOfferImage());
	                if(entity.getOfferOriginalPrice()!=null)
	                	carrybag.setOfferOriginalPrice(entity.getOfferOriginalPrice());
	                if(entity.getOfferDiscount()!=null)
	                	carrybag.setOfferDiscount(entity.getOfferDiscount());
	                if(entity.getOfferValidity()!=null)
	                	carrybag.setOfferValidity(entity.getOfferValidity());
	                if(entity.getVendorId()!=null)
	                	carrybag.setVendorId(entity.getVendorId());
	                carryBagRepository.save(entity);
	                flag = 1;
	            }
	        }
	        if(flag==1)        
	            return new ResponseEntity<>(HttpStatus.OK);            
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        
	   }
	
	//Fallback method for adding the offer to the list
	 public ResponseEntity<Object> fallbackUpdateCarryBag(@RequestBody CarryBagBean entity) {
			
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
			
		}
	
	
	
	@HystrixCommand(fallbackMethod="fallbackDeleteFromCarryBag")
	@DeleteMapping("/bag/offer-id/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable String id) {
		
		 if(carryBagRepository.existsByofferId(id)) {
		carryBagRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		 }
		 
		 else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
		 
		
		    

	//Fallback method for delete the offer from the list
	public ResponseEntity<Object> fallbackDeleteFromCarryBag(@PathVariable String id) {
		

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);


}
	
	
}
