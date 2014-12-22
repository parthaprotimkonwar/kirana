package com.beans;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.generic.rest.mvc.LandingController;
import com.generic.rest.mvc.LoginControllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
public class EmployeeTest {

	@InjectMocks
	LoginControllers controller;
	
	@InjectMocks
	LandingController locationController;
	//creates mockmvc objects
	private MockMvc mockmvc;
	
	@Before
	public void setup(){
		
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void test() throws Exception{
		/*mockmvc.perform(post("/secretEmployee").contentType(MediaType.APPLICATION_JSON).
				content("{\"name\":\"partha\"}")).
		andDo(print());
		*/
		/*mockmvc.perform(post("/secretEmployeeArray").contentType(MediaType.APPLICATION_JSON).
				content("[{\"name\":\"partha\"},{\"name\":\"protim\"}]")).
		andDo(print());*/
		/*mockmvc.perform(get("/secretEmployee1")).andDo(print());*/
		mockmvc.perform(get("/employee/links/5")).andDo(print());
		
		/*mockmvc.perform(get("/rest/location/locations")).andDo(print());*/
		
	}
}
