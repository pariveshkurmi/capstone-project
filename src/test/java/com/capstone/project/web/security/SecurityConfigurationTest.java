package com.capstone.project.web.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)*/
public class SecurityConfigurationTest {

	/*@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	SecurityConfiguration secConfig;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void accessProtected() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(unauthenticated()).andExpect(status().isUnauthorized());
	}

	@Test
	public void loginUser() throws Exception {
		this.mockMvc.perform(get("/").with(httpBasic("user", "password"))).andExpect(status().isOk());
	}

	@Test
	public void loginInvalidUser() throws Exception {
		this.mockMvc.perform(formLogi().user("invalid").password("invalid")).andExpect(unauthenticated());
	}*/
}
