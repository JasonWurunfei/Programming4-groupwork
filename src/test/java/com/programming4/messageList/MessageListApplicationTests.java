package com.programming4.messageList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test the SpringMVC.
 *
 * <p>Testing whether it could properly display the correct page.</p>
 *
 * @author Wu Runfei, LiuYing
 */
@SpringBootTest
@AutoConfigureMockMvc
class MessageListApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void homepage() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	void listPage() throws Exception {
		this.mockMvc.perform(get("/message/list")).andExpect(status().isOk());
	}

	@Test
	void postPage() throws Exception {
		this.mockMvc.perform(get("/message/post")).andExpect(status().isOk());
	}

}
