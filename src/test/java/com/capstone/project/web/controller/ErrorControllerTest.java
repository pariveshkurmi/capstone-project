package com.capstone.project.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorControllerTest {

	@Mock
	HttpServletRequest request;
	@Mock
	Exception exception;
	@InjectMocks
	ErrorController errorController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		Mockito.when(request.getRequestURL()).thenReturn(new StringBuffer());
		Mockito.when(exception.getLocalizedMessage()).thenReturn("localizedValue");
		ModelAndView andView = errorController.handleException(request, exception);

		Assert.assertEquals("localizedValue", andView.getModel().get("exception"));
	}
}
