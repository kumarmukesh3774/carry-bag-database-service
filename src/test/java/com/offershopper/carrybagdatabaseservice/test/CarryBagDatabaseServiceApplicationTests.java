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
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.offershopper.carrybagdatabaseservice.model.CarryBagBean;
import com.offershopper.carrybagdatabaseservice.repository.CarryBagRepository;
import com.offershopper.carrybagdatabaseservice.resource.CarryBagController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CarryBagController.class)
/*
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @WebMvcTest
 */
// @SpringBootTest
public class CarryBagDatabaseServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CarryBagRepository carryBagRepository;
	@InjectMocks
	private CarryBagController carryBagController;

	@Before
	public void setUp() throws Exception {
		// System.out.println(jsonContent.toString());
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(carryBagController).build();
	}

	/*
	 * @Test public void testBag() throws Exception {
	 * 
	 * mockMvc.perform(get("/bag1")) .andExpect(status().isNotFound())
	 * .andExpect(content().string("Hello")); }
	 */
	// Positive test for getAllByUserId
	@Test
	public void testGetAllByUserId() throws Exception {

		List<CarryBagBean> carryBagBeans = new ArrayList<>();
		// carryBags.add(new CarryBagBean("101"));
		carryBagBeans.add(new CarryBagBean("rakesh_005", "offer_0015", "offer_image", "offer_title",
				405l, 365l, "22-05-2018","vendor_001"));
		Mockito.when(carryBagRepository.findByUserId("rakesh_005")).thenReturn(carryBagBeans);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bag/user-id/rakesh_005")
				.accept(MediaType.APPLICATION_JSON);
		try {

			MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
			System.out.println("\n" + result.getResponse().getContentAsString() + "\nHello\n");

			String expected = "[{\"userId\":\"rakesh_005\"," 
					+ "\"offerId\":\"offer_0015\","
					+ "\"offerImage\":\"offer_image\"," 
					+ "\"offerTitle\":\"offer_title\","
					+ "\"offerOriginalPrice\":405," 
					+ "\"offerDiscount\":365," 
					+ "\"offerValidity\":\"22-05-2018\","
					+ "\"vendorId\":\"vendor_001\"}]";

			// String expected =
			// "[{\"kkdCustId\":\"aa\",\"kkdFarmerId\":\"bb\",\"orderId\":\"cc\",\"rating\":3.14,\"review\":\"dd\"}]";
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
			// JSONAssert.assertNotEquals(expectedNegative,
			// result.getResponse().getContentAsString(), false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/*
		 * Mockito.when(carryBagRepository.findAll()).thenReturn(carryBags);
		 * 
		 * MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/bag")
		 * .accept(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) //
		 * .andExpect(model().attribute("carryBags", Matchers.hasSize(2))) .andReturn()
		 * ;
		 * 
		 * System.out.println(mvcResult+"=============================================="
		 * ); verify(carryBagRepository).findAll();
		 */
	}

	// Negative test for getAllByUserId
	@Test
	public void testAllBagNegative() throws Exception {

		List<CarryBagBean> carryBagBeans = new ArrayList<>();
		// carryBags.add(new CarryBagBean("101"));
		carryBagBeans.add(new CarryBagBean("rakesh_005", "offer_0015", "offer_image", "offer_title", 405l, 365l, "22-05-2018",
				"vendor_001"));
		Mockito.when(carryBagRepository.findByUserId("rakesh_005")).thenReturn(carryBagBeans);
		// Mockito.when(carryBagRepository.findByUserId("rakesh_005")).thenReturn(carryBags);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bag/user-id/rakesh_005")
				.accept(MediaType.APPLICATION_JSON);
		try {

			MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
			System.out.println("\n" + result.getResponse().getContentAsString() + "\nHello\n");

			String expectedNegative = "[{\"userId\":\"rakesh_005\"," 
					+ "\"offerId\":\"offer_0015\","
					+ "\"offerImage\":\"offer_image\"," 
					+ "\"offerTitle\":\"offer_title\","
					+ "\"offerOriginalPrice\":405," 
					+ "\"offerDiscount\":365," 
					+ "\"offerValidity\":\"29-05-2018\","
					+ "\"vendorId\":\"vendor_001\"}]";
			JSONAssert.assertNotEquals(expectedNegative, result.getResponse().getContentAsString(), false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/*
		 * Mockito.when(carryBagRepository.findAll()).thenReturn(carryBags);
		 * 
		 * MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/bag")
		 * .accept(MediaType.APPLICATION_JSON)) .andExpect(status().isOk()) //
		 * .andExpect(model().attribute("carryBags", Matchers.hasSize(2))) .andReturn()
		 * ;
		 * 
		 * System.out.println(mvcResult+"=============================================="
		 * ); verify(carryBagRepository).findAll();
		 */
	}

	// Positive test for addToCarryBag
	@Test
	public void testPostPositiveMapping() {
		String expected = "{\"userId\":\"rakesh_005\",\"offerId\":\"offer_005\",\"offerImage\":\"offer_image\",\"offerTitle\":\"offer_title\",\"offerOriginalPrice\":405,\"offerDiscount\":365,\"offerValidity\":\"22-05-2018\",\"vendorId\":\"vendor_001\"}";

			CarryBagBean bag = new CarryBagBean("rakesh_005", "offer_005", "offer_image", "offer_title", 405l, 365l,
					"22-05-2018", "vendor_001");
			Mockito.when(carryBagRepository.existsById("offer_005")).thenReturn(false);
			Mockito.when(carryBagRepository.insert(Mockito.any(CarryBagBean.class))).thenReturn(bag);
			
			
			// sending data in the form of packets
			RequestBuilder positiveRequestBuilder = MockMvcRequestBuilders.post("/bag/add").accept(MediaType.APPLICATION_JSON)
					.content(expected).contentType(MediaType.APPLICATION_JSON);
			try {
			 MvcResult resultPositive = mockMvc.perform(positiveRequestBuilder).andReturn();
	            MockHttpServletResponse responsePositive = resultPositive.getResponse();
	            assertEquals(HttpStatus.CREATED.value(), responsePositive.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		
		

	}
	
	
	
	@Test
	public void testPostNegativeMapping() {
		String expectedInvalid = "{\"userId\":\"rakesh_005\",\"offerId\":\"offer_005\",\"offerImage\":\"offer_image\",\"offerTitle\":\"offer_title\",\"offerOriginalPrice\":405,\"offerDiscount\":365,\"offerValidity\":\"22-05-2018\",\"vendorId\":\"vendor_001\"}";

			CarryBagBean bag = new CarryBagBean("rakesh_005", "offer_005", "offer_image", "offer_title", 405l, 365l,
					"22-05-2018", "vendor_001");
			Mockito.when(carryBagRepository.existsById("offer_005")).thenReturn(true);
			Mockito.when(carryBagRepository.insert(Mockito.any(CarryBagBean.class))).thenReturn(bag);
			
			
			// sending data in the form of packets
			RequestBuilder negativeRequestBuilder = MockMvcRequestBuilders.post("/bag/add").accept(MediaType.APPLICATION_JSON)
					.content(expectedInvalid).contentType(MediaType.APPLICATION_JSON);
			try {
				  MvcResult resultNegative = mockMvc.perform(negativeRequestBuilder).andReturn();
		            MockHttpServletResponse responseNegative = resultNegative.getResponse();
		            assertNotEquals(HttpStatus.OK.value(), responseNegative.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
			

	}
	
	
	 @Test
	    public void testDeletePositiveMapping() {
		 
		 String expectedvalid= "{\"userId\":\"rakesh_005\",\"offerId\":\"offer_005\",\"offerImage\":\"offer_image\",\"offerTitle\":\"offer_title\",\"offerOriginalPrice\":405,\"offerDiscount\":365,\"offerValidity\":\"22-05-2018\",\"vendorId\":\"vendor_001\"}";

			CarryBagBean bag = new CarryBagBean("rakesh_005", "offer_005", "offer_image", "offer_title", 405l, 365l,
					"22-05-2018", "vendor_001");
	        Mockito.when(carryBagRepository.existsByofferId("offer_005")).thenReturn(true);
	        RequestBuilder positiveRequestBuilder = MockMvcRequestBuilders.delete("/bag/offer-id/offer_005")
	                .accept(MediaType.APPLICATION_JSON);
	        try {
	            MvcResult resultPositive = mockMvc.perform(positiveRequestBuilder).andReturn();
	            MockHttpServletResponse responsePositive = resultPositive.getResponse();
	            assertEquals(HttpStatus.OK.value(), responsePositive.getStatus());
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 @Test
	    public void testDeleteNegativeMapping() {
		 
		 String expectedInvalid= "{\"userId\":\"rakesh_005\",\"offerId\":\"offer_005\",\"offerImage\":\"offer_image\",\"offerTitle\":\"offer_title\",\"offerOriginalPrice\":405,\"offerDiscount\":365,\"offerValidity\":\"22-05-2018\",\"vendorId\":\"vendor_001\"}";

			CarryBagBean bag = new CarryBagBean("rakesh_005", "offer_005", "offer_image", "offer_title", 405l, 365l,
					"22-05-2018", "vendor_001");
	        Mockito.when(carryBagRepository.existsByofferId("offer_005")).thenReturn(false);
	        RequestBuilder positiveRequestBuilder = MockMvcRequestBuilders.delete("/bag/offer-id/offer_005")
	                .accept(MediaType.APPLICATION_JSON);
	        try {
	            MvcResult resultPositive = mockMvc.perform(positiveRequestBuilder).andReturn();
	            MockHttpServletResponse responsePositive = resultPositive.getResponse();
	            assertNotEquals(HttpStatus.OK.value(), responsePositive.getStatus());
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }

	 
	 @Test
	    public void testPositiveUpdateMapping() {
		  List<CarryBagBean> carrybagList = new ArrayList<CarryBagBean>();
		 String expectedvalid= "{\"userId\":\"rakesh_005\",\"offerId\":\"offer_005\",\"offerImage\":\"offer_image\",\"offerTitle\":\"offer_title\",\"offerOriginalPrice\":405,\"offerDiscount\":365,\"offerValidity\":\"22-05-2018\",\"vendorId\":\"vendor_001\"}";

			CarryBagBean bag = new CarryBagBean("rakesh_005", "offer_005", "offer_image", "offer_title", 405l, 365l,
					"22-05-2018", "vendor_001");
			carrybagList.add(bag);
	        Mockito.when(carryBagRepository.findAll()).thenReturn(carrybagList);
	        RequestBuilder positiveRequestBuilder = MockMvcRequestBuilders.put("/bag/update")
	                .accept(MediaType.APPLICATION_JSON).content(expectedvalid)
	                .contentType(MediaType.APPLICATION_JSON);
	        try {
	            MvcResult resultPositive = mockMvc.perform(positiveRequestBuilder).andReturn();
	            MockHttpServletResponse responsePositive = resultPositive.getResponse();
	            assertEquals(HttpStatus.OK.value(), responsePositive.getStatus());
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 
	 @Test
	    public void testNegativeUpdateMapping() {
		  List<CarryBagBean> carrybagList = new ArrayList<CarryBagBean>();
		 String expectedInvalid= "{\"userId\":\"rakesh_005\",\"offerId\":\"offer_005\",\"offerImage\":\"offer_image\",\"offerTitle\":\"offer_title\",\"offerOriginalPrice\":405,\"offerDiscount\":365,\"offerValidity\":\"22-05-2018\",\"vendorId\":\"vendor_001\"}";

			CarryBagBean bag = new CarryBagBean("rakesh_005", "offer_006", "offer_image", "offer_title", 405l, 365l,
					"22-05-2018", "vendor_001");
			carrybagList.add(bag);
	        Mockito.when(carryBagRepository.findAll()).thenReturn(carrybagList);
	        RequestBuilder negativeRequestBuilder = MockMvcRequestBuilders.put("/bag/update")
	                .accept(MediaType.APPLICATION_JSON).content(expectedInvalid)
	                .contentType(MediaType.APPLICATION_JSON);
	        try {
	            MvcResult resultPositive = mockMvc.perform(negativeRequestBuilder).andReturn();
	            MockHttpServletResponse responsePositive = resultPositive.getResponse();
	            assertNotEquals(HttpStatus.OK.value(), responsePositive.getStatus());
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
}
