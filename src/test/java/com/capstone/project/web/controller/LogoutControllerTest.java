package com.capstone.project.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogoutControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	Exception exception;
	@Mock
	SecurityContextHolder sec;
	@Mock
	LogoutController loginController;
	@Mock
	SecurityContext securityContext;
	Authentication authentication;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		authentication = Mockito.mock(Authentication.class);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		String testString = loginController.logout(request, response);

		Assert.assertNull(testString);
	}
}
