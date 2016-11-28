package net.dracoblue.spring.web.mvc.method.annotation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HttpResponseHeadersTestApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0") 
public class ExpressionControllerTest 
{
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testValueExpression() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
				.get("/handleValueExpression"))
				.andReturn().getResponse();
		assertNotNull(response.getHeader("X-Key"));
		assertTrue(response.getHeader("X-Key").equals("X-Value-Method"));
		assertEquals(1, response.getHeaderNames().size());
	}
	
	@Test
	public void testMultipleValueExpressions() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
				.get("/handleMultipleValueExpressions"))
				.andReturn().getResponse();
		
		assertNotNull(response.getHeader("X-Key-Two"));
		assertTrue(response.getHeader("X-Key-Two").equals("X-Value-Two-Method"));
		assertNotNull(response.getHeader("X-Key-One"));
		assertTrue(response.getHeader("X-Key-One").equals("X-Value-One-Method"));
		assertEquals(2, response.getHeaderNames().size());
	}

	@Test
	public void testNameExpression() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
				.get("/handleNameExpression"))
				.andReturn().getResponse();
		
		assertNotNull(response.getHeader("X-Key"));
		assertTrue(response.getHeader("X-Key").equals("X-Value-Method"));
		assertEquals(1, response.getHeaderNames().size());
	}
	
	@Test
	public void testMultipleNameExpressions() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
				.get("/handleMultipleNameExpressions"))
				.andReturn().getResponse();
		
		assertNotNull(response.getHeader("X-Key-Two"));
		assertTrue(response.getHeader("X-Key-Two").equals("X-Value-Two-Method"));
		assertNotNull(response.getHeader("X-Key-One"));
		assertTrue(response.getHeader("X-Key-One").equals("X-Value-One-Method"));
		assertEquals(2, response.getHeaderNames().size());
	}

	@Test
	public void testNameAndValueExpression() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
				.get("/handleNameAndValueExpression"))
				.andReturn().getResponse();

		assertNotNull(response.getHeader("X-Key"));
		assertTrue(response.getHeader("X-Key").equals("X-Value-Method"));
		assertEquals(1, response.getHeaderNames().size());
	}
	
	@Test
	public void testMultipleNameAndValueExpressions() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
				.get("/handleMultipleNameAndValueExpressions"))
				.andReturn().getResponse();
		
		assertNotNull(response.getHeader("X-Key-Two"));
		assertTrue(response.getHeader("X-Key-Two").equals("X-Value-Two-Method"));
		assertNotNull(response.getHeader("X-Key-One"));
		assertTrue(response.getHeader("X-Key-One").equals("X-Value-One-Method"));
		assertEquals(2, response.getHeaderNames().size());
	}

	@Test
	public void testCalculation() throws Exception {
		MockHttpServletResponse response = this.mockMvc
			.perform(MockMvcRequestBuilders
			.get("/handleCalculation"))
			.andReturn().getResponse();
		
		assertNotNull(response.getHeader("Cache-Control"));
		assertTrue(response.getHeader("Cache-Control").equals("max-age=300"));
	}
	
	@Test
	public void testSpringPropertyExpressions() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders
			.get("/handlePropertyExpressions"))
			.andExpect(
				MockMvcResultMatchers.header().string("X-Java-Version", System.getProperty("java.version"))
			);
	}

	@Test
	public void testOptionsRequest() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
						.options("/handleCalculation"))
				.andReturn().getResponse();

		assertNull(response.getHeader("Cache-Control"));
	}

	@Test
	public void testCorsOptionsRequest() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
						.options("/handleCalculation")
						.header("Access-Control-Request-Method", "POST")
						.header("Origin", "https://localhost:8080")
				)
				.andReturn().getResponse();

		assertNull(response.getHeader("Cache-Control"));
	}

	@Test
	public void testGetRequestWithCorsHeaders() throws Exception {
		MockHttpServletResponse response = this.mockMvc
				.perform(MockMvcRequestBuilders
						.get("/handleCalculation")
						.header("Access-Control-Request-Method", "POST")
						.header("Origin", "https://localhost:8080")
				)
				.andReturn().getResponse();

		assertNotNull(response.getHeader("Cache-Control"));
		assertTrue(response.getHeader("Cache-Control").equals("max-age=300"));
	}
}

