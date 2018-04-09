package com.project.os.carrybagdatabaseservice;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.os.carrybagdatabaseservice.carrybag.CarryBag;
import com.project.os.carrybagdatabaseservice.repository.CarryBagRepository;
import com.project.os.carrybagdatabaseservice.resource.CarryBagController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
//@SpringBootTest
public class CarryBagDatabaseServiceApplicationTests {
	
/*	String jsonContent = "{\"userId\":\"rakesh_002\",\"offerId\":\"offer_002\","
			+"\"offerImage\":\"offer_image\",\"offerTitle\":\"offer_title\",\"offerOriginalPrice\":\"550\","
			+ "\"offerDiscount\":\"500\",\"offerValidity\":\"22-05-2018\","
			+ "\"vendorId\":\"vendor_001\"}";*/   
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CarryBagRepository carryBagRepository;
	@InjectMocks
	private CarryBagController carryBagController; 
	
	@Before
	public void setUp() throws Exception{
	//	System.out.println(jsonContent.toString());
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(carryBagController).build();
	}
	

/*	@Test
	public void testBag() throws Exception {
		
		mockMvc.perform(get("/bag1"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("Hello"));
	}
	*/
	
	@Test
	public void testAllBag() throws Exception {
		

		
		List <CarryBag> carryBags=new  ArrayList<>();
		carryBags.add(new CarryBag("hgh","hgh","hgh","hgh",455l,400l,"hgh","hgh"));
		carryBags.add(new CarryBag("hgh2","hgh","hgh","hgh",488l,444l,"hgh","hgh"));

		Mockito.when(carryBagRepository.findAll()).thenReturn((List)carryBags);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/bag")
	               .accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
	//	.andExpect(model().attribute("carryBags", Matchers.hasSize(2)))
		.andReturn()
		;
		
		System.out.println(mvcResult+"==============================================");
		//verify(carryBagRepository).findAll();
	}


}
