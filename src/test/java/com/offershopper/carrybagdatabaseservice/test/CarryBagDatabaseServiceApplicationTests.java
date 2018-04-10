package com.offershopper.carrybagdatabaseservice.test;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.offershopper.carrybagdatabaseservice.model.CarryBag;
import com.offershopper.carrybagdatabaseservice.repository.CarryBagRepository;
import com.offershopper.carrybagdatabaseservice.resource.CarryBagController;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CarryBagController.class)
/*@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest*/
//@SpringBootTest
public class CarryBagDatabaseServiceApplicationTests {
	

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
		//carryBags.add(new CarryBag("101"));
		carryBags.add(new CarryBag("rakesh_002","offer_002","offer_image","offer_title",550l,500l,"22-05-2018","vendor_001"));
		Mockito.when(carryBagRepository.findAll()).thenReturn(carryBags);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bag").accept(MediaType.APPLICATION_JSON);
		try {

			MvcResult result = mockMvc.perform(requestBuilder)
					.andExpect(status().isOk())
					.andReturn();
			System.out.println("\n" + result.getResponse().getContentAsString() + "\nHello\n");
			
			String expected="[{\"userId\":\"rakesh_002\","
					+ "\"offerId\":\"offer_002\","
					+ "\"offerImage\":\"offer_image\","
					+ "\"offerTitle\":\"offer_title\","
					+ "\"offerOriginalPrice\":550,"
					+ "\"offerDiscount\":500,"
					+ "\"offerValidity\":\"22-05-2018\","
					+ "\"vendorId\":\"vendor_001\"}]";
			//String expected = "[{\"kkdCustId\":\"aa\",\"kkdFarmerId\":\"bb\",\"orderId\":\"cc\",\"rating\":3.14,\"review\":\"dd\"}]";
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


/*		Mockito.when(carryBagRepository.findAll()).thenReturn(carryBags);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/bag")
	               .accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
	//	.andExpect(model().attribute("carryBags", Matchers.hasSize(2)))
		.andReturn()
		;
		
		System.out.println(mvcResult+"==============================================");
		verify(carryBagRepository).findAll();*/
	}
	
	
	@Test
	public void testPostMapping() {
		String exampleRating = "{\"userId\":\"101\"}";
		try {
			
			CarryBag bag= new CarryBag("106");
			Mockito.when(carryBagRepository.save(Mockito.any(CarryBag.class))).thenReturn(bag);
			//Mockito.when(carryBagRepository.save(Mockito.any(CarryBag.class))).thenReturn(bag);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bag")
					.accept(MediaType.APPLICATION_JSON).content(exampleRating).contentType(MediaType.APPLICATION_JSON);
			MvcResult result;
			result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			JSONAssert.assertEquals(exampleRating, result.getResponse().getContentAsString(), false);
			/*assertEquals("http://localhost/students/Student1/courses/1",
					response.getHeader(HttpHeaders.LOCATION));*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
/*	@Test
	public void testBag() throws Exception {
		String exampleRating = "{\"userId\":\"101\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/bag/offer-id/101")
				.accept(MediaType.APPLICATION_JSON).content(exampleRating).contentType(MediaType.APPLICATION_JSON);
		
		
		
		MvcResult result;
		result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		JSONAssert.assertEquals(exampleRating, result.getResponse().getContentAsString(), false);
		//mockMvc.perform(delete("/bag/offer-id/101"))
		//.andExpect(content().string(exampleRating));
	}
*/

}
