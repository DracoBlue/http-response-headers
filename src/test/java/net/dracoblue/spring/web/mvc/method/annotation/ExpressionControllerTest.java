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
public class ExpressionControllerTest 
{
	private HttpResponseHeaderHandlerInterceptor interceptor;
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	
	private final ExpressionController controller = new ExpressionController();
	
	@Before
	public void setUp() {
		interceptor = new HttpResponseHeaderHandlerInterceptor();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void testValueExpression() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleValueExpression"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key"));
		assertTrue(response.getHeader("X-Key").equals("X-Value-Method"));
		assertEquals(1, response.getHeaderNames().size());
	}
	
	@Test
	public void testMultipleValueExpressions() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleMultipleValueExpressions"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-Two"));
		assertTrue(response.getHeader("X-Key-Two").equals("X-Value-Two-Method"));
		assertNotNull(response.getHeader("X-Key-One"));
		assertTrue(response.getHeader("X-Key-One").equals("X-Value-One-Method"));
		assertEquals(2, response.getHeaderNames().size());
	}

	@Test
	public void testNameExpression() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleNameExpression"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key"));
		assertTrue(response.getHeader("X-Key").equals("X-Value-Method"));
		assertEquals(1, response.getHeaderNames().size());
	}
	
	@Test
	public void testMultipleNameExpressions() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleMultipleNameExpressions"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-Two"));
		assertTrue(response.getHeader("X-Key-Two").equals("X-Value-Two-Method"));
		assertNotNull(response.getHeader("X-Key-One"));
		assertTrue(response.getHeader("X-Key-One").equals("X-Value-One-Method"));
		assertEquals(2, response.getHeaderNames().size());
	}

	@Test
	public void testNameAndValueExpression() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleNameAndValueExpression"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key"));
		assertTrue(response.getHeader("X-Key").equals("X-Value-Method"));
		assertEquals(1, response.getHeaderNames().size());
	}
	
	@Test
	public void testMultipleNameAndValueExpressions() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleMultipleNameAndValueExpressions"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("X-Key-Two"));
		assertTrue(response.getHeader("X-Key-Two").equals("X-Value-Two-Method"));
		assertNotNull(response.getHeader("X-Key-One"));
		assertTrue(response.getHeader("X-Key-One").equals("X-Value-One-Method"));
		assertEquals(2, response.getHeaderNames().size());
	}

	@Test
	public void testCalculation() throws Exception {
		
		final HandlerMethod handler = new HandlerMethod(
				controller, 
				controller.getClass().getMethod("handleCalculation"));
		
		interceptor.preHandle(request, response, handler);
		assertNotNull(response.getHeader("Cache-Control"));
		assertTrue(response.getHeader("Cache-Control").equals("max-age=300"));
		assertEquals(1, response.getHeaderNames().size());
	}
}

