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
public class FullyAnnotatedControllerTest 
{
	private HttpResponseHeaderHandlerInterceptor interceptor;
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	
	private final FullyAnnotatedTestController controller = new FullyAnnotatedTestController();
	
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
		assertNotNull(response.getHeader("X-Key-For-Response-Header"));
		assertTrue(response.getHeader("X-Key-For-Response-Header").equals("X-Value-For-Response-Header-Class"));
		assertNotNull(response.getHeader("X-Key-For-Response-Headers-One"));
		assertTrue(response.getHeader("X-Key-For-Response-Headers-One").equals("X-Value-For-Response-Headers-One-Class"));
		assertNotNull(response.getHeader("X-Key-For-Response-Headers-Two"));
		assertTrue(response.getHeader("X-Key-For-Response-Headers-Two").equals("X-Value-For-Response-Headers-Two-Class"));
		assertEquals(3, response.getHeaderNames().size());
	}
	
	@Test
	public void testWithSimpleExtraMethodAnnotationAndAllClassAnnotations() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleExtraAnnotatedMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-For-Response-Headers-One"));
		assertTrue(response.getHeader("X-Key-For-Response-Headers-One").equals("X-Value-For-Response-Headers-One-Class"));
		assertNotNull(response.getHeader("X-Key-For-Response-Headers-Two"));
		assertTrue(response.getHeader("X-Key-For-Response-Headers-Two").equals("X-Value-For-Response-Headers-Two-Class"));
		assertNotNull(response.getHeader("X-Key"));
		assertTrue(response.getHeader("X-Key").equals("X-Value-Method"));
		assertEquals(3, response.getHeaderNames().size());
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
		assertNotNull(response.getHeader("X-Key-For-Response-Header"));
		assertTrue(response.getHeader("X-Key-For-Response-Header").equals("X-Value-For-Response-Header-Class"));
		assertEquals(3, response.getHeaderNames().size());
	}
	
	@Test
	public void testWithMultpleEmptyMethodAnnotationsAndAllClassAnnotations() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleWithEmptyMultipleExtraAnnotatedMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-For-Response-Header"));
		assertTrue(response.getHeader("X-Key-For-Response-Header").equals("X-Value-For-Response-Header-Class"));
		assertEquals(1, response.getHeaderNames().size());
	}

	@Test
	public void testOverrideClassAnnotation() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleOverrideClassAnnotationMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-For-Response-Header"));
		assertTrue(response.getHeader("X-Key-For-Response-Header").equals("X-Value-Method"));
		assertNotNull(response.getHeader("X-Key-For-Response-Headers-One"));
		assertTrue(response.getHeader("X-Key-For-Response-Headers-One").equals("X-Value-For-Response-Headers-One-Class"));
		assertNotNull(response.getHeader("X-Key-For-Response-Headers-Two"));
		assertTrue(response.getHeader("X-Key-For-Response-Headers-Two").equals("X-Value-For-Response-Headers-Two-Class"));
		assertEquals(3, response.getHeaderNames().size());
	}
	
	@Test
	public void testOverrideClassAnnotationWithOneHeaderInMultipleHeaders() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleOverrideClassAnnotationWithOneHeaderInMultipleHeadersMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-For-Response-Header"));
		assertTrue(response.getHeader("X-Key-For-Response-Header").equals("X-Value-Method"));
		assertEquals(1, response.getHeaderNames().size());
	}
	
	@Test
	public void testOverrideClassAnnotationWithOneHeaderInSingleHeaders() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleOverrideClassAnnotationWithSingleHeaderMethod"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-For-Response-Headers-One"));
		assertTrue(response.getHeader("X-Key-For-Response-Headers-One").equals("X-Value-For-Response-Headers-One-Method"));
		assertNotNull(response.getHeader("X-Key-For-Response-Headers-Two"));
		assertTrue(response.getHeader("X-Key-For-Response-Headers-Two").equals("X-Value-For-Response-Headers-Two-Class"));
		assertEquals(2, response.getHeaderNames().size());
	}
}
