package com.project.os.carrybagdatabaseservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.netflix.discovery.shared.Application;
import com.project.os.carrybagdatabaseservice.carrybag.CarryBag;
import com.project.os.carrybagdatabaseservice.repository.CarryBagRepository;
import com.project.os.carrybagdatabaseservice.resource.CarryBagController;

@RunWith(SpringRunner.class)
@WebMvcTest
@SpringBootTest
public class CarryBagDatabaseServiceApplicationTests {
	
/*	String jsonContent = "{\"userId\":\"rakesh_002\",\"offerId\":\"offer_002\","
			+"\"offerImage\":\"offer_image\",\"offerTitle\":\"offer_title\",\"offerOriginalPrice\":\"550\","
			+ "\"offerDiscount\":\"500\",\"offerValidity\":\"22-05-2018\","
			+ "\"vendorId\":\"vendor_001\"}";*/   

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
		

		
		List <CarryBag> list=new  ArrayList<>();
				list.add(new CarryBag("hgh","hgh","hgh","hgh",455l,400l,"hgh","hgh"));
				list.add(new CarryBag("hgh2","hgh","hgh","hgh",488l,444l,"hgh","hgh"));

		Mockito.when(carryBagRepository.findAll()).thenReturn(Collections.emptyList());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/bag")
	               .accept(MediaType.APPLICATION_JSON))
		.andReturn();
		verify(carryBagRepository).findAll();
	}


}
