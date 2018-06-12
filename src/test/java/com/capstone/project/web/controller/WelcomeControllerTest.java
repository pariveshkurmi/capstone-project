package com.capstone.project.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WelcomeControllerTest {

	@Mock
	SecurityContext securityContext;
	Authentication authentication;
	@InjectMocks
	WelcomeController welcome;

	@SuppressWarnings("static-access")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		authentication = Mockito.mock(Authentication.class);
		// securityContext = Mockito.mock(SecurityContext.class);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		ModelMap map = new ModelMap();
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		Mockito.when(authentication.getPrincipal()).thenReturn(new Object());
		SecurityContextHolder.setContext(securityContext);
		String a = welcome.showWelcomePage(map);

		Assert.assertEquals("welcome", a);

	}
}
