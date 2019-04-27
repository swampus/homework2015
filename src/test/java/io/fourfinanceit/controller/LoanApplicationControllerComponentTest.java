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
public class LoanApplicationControllerComponentTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getLoanApplicationById() throws Exception {
		final String expectedAnswer = "{\"id\":1,\"phoneNumber\":null,\"dayCountTerm\":10,\"amount\":10,"
				+ "\"remoteIp\":null,\"timestampDayCreatedAt\":null,\"closed\":null}";

		mvc.perform(MockMvcRequestBuilders.get("/applications/id/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(expectedAnswer)));
	}

	@Test
	public void getAllLoanApplicationByPhoneNum() throws Exception {
		final String expectedAnswer = "[{\"id\":1,\"phoneNumber\":null,\"dayCountTerm\":10,\"amount\":10,"
				+ "\"remoteIp\":null,\"timestampDayCreatedAt\":null,\"closed\":null}]";

		mvc.perform(MockMvcRequestBuilders.get("/applications/phone_num/33"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(expectedAnswer)));
	}


}