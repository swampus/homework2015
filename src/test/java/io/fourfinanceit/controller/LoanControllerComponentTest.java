package io.fourfinanceit.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerComponentTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getLoansByPhoneNum() throws Exception {
		final String expectedAnswer = "[{\"id\":null,\"phoneNum\":\"33\",\"amount\":10,"
				+ "\"loanStatus\":\"ACTIVE\",\"termInDays\":20},"
				+ "{\"id\":null,\"phoneNum\":\"33\",\"amount\":5,\"loanStatus\":\"ACTIVE\",\"termInDays\":25},"
				+ "{\"id\":null,\"phoneNum\":\"33\",\"amount\":11,\"loanStatus\":\"CLOSED\""
				+ ",\"termInDays\":22}]";
		mvc.perform(MockMvcRequestBuilders.get("/loans/loan/phone_num/33"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(expectedAnswer)));
	}

	@Test
	public void getLoanById() throws Exception {
		final String expectedAnswer = "{\"id\":null,\"phoneNum\":\"33\",\"amount\":10,\"loanStatus\":\"ACTIVE\",\"termInDays\":20}";

		mvc.perform(MockMvcRequestBuilders.get("/loans/loan/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(expectedAnswer)));
	}

}