package net.dracoblue.spring.web.mvc.method.annotation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import net.dracoblue.spring.web.mvc.method.annotation.HttpResponseHeaderHandlerInterceptor;

/**
 * Unit test for simple App.
 */
public class NotAnnotatedControllerTest 
{
	private HttpResponseHeaderHandlerInterceptor interceptor;
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	
	private final NotAnnotatedTestController controller = new NotAnnotatedTestController();
	
	@Before
	public void setUp() {
		interceptor = new HttpResponseHeaderHandlerInterceptor();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void testWithoutAnyMethodAnnotationAtAllButAllClassAnnotations() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleNotAnnotatedMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertEquals(0, response.getHeaderNames().size());
	}
	
	@Test
	public void testWithSimpleExtraMethodAnnotationAndAllClassAnnotations() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleExtraAnnotatedMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key"));
		assertTrue(response.getHeader("X-Key").equals("X-Value-Method"));
		assertEquals(1, response.getHeaderNames().size());
	}
	
	@Test
	public void testWithMultipleExtraMethodAnnotationsAndAllClassAnnotations() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleMultipleExtraAnnotatedMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-Two"));
		assertTrue(response.getHeader("X-Key-Two").equals("X-Value-Two-Method"));
		assertNotNull(response.getHeader("X-Key-One"));
		assertTrue(response.getHeader("X-Key-One").equals("X-Value-One-Method"));
		assertEquals(2, response.getHeaderNames().size());
	}
	
	@Test
	public void testWithMultpleEmptyMethodAnnotationsAndAllClassAnnotations() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleWithEmptyMultipleExtraAnnotatedMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertEquals(0, response.getHeaderNames().size());
	}
	
}
