package com.springBoot.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest

class AppApplicationTests {
	@Autowired
    MockMvc mockMvc;
	
	@MockBean
    private AppControlle appController;

	@Test
	void contextLoads() throws Exception {
		MvcResult mvcResult;
		when(appController.converts("1")).thenReturn("I");
		mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/1"))
				.andExpect(MockMvcResultMatchers.content().string("I")).andReturn();

		String actualResponseBody = mvcResult.getResponse().getContentAsString();

		assertThat(actualResponseBody).isEqualToIgnoringWhitespace("V");

		when(appController.converts("4")).thenReturn("IV");
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/4")).andExpect(MockMvcResultMatchers.content().string("IV"))
				.andReturn();

		when(appController.converts("90")).thenReturn("XC");
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/90"))
				.andExpect(MockMvcResultMatchers.content().string("XC"));

		when(appController.converts("900")).thenReturn("CM");
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/900"))
				.andExpect(MockMvcResultMatchers.content().string("CM"));

		when(appController.converts("1903")).thenReturn("MCMIII");
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/1903"))
				.andExpect(MockMvcResultMatchers.content().string("MCMIII"));

		when(appController.converts("1997")).thenReturn("MCMXCVII");
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/1997"))
				.andExpect(MockMvcResultMatchers.content().string("MCMXCVII"));

		when(appController.converts("4000")).thenReturn("MMMM");
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/4000"))
				.andExpect(MockMvcResultMatchers.content().string("MMMM"));
		
		when(appController.converts("0")).thenReturn("Cannot be converted to Roman Numeral");
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/0"))
				.andExpect(MockMvcResultMatchers.content().string("Cannot be converted to Roman Numeral"));
		
		when(appController.converts("-1")).thenReturn("Cannot be converted to Roman Numeral");
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/app/-1"))
				.andExpect(MockMvcResultMatchers.content().string("Cannot be converted to Roman Numeral"));
	}

}
