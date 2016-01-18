package net.dracoblue.spring.web.mvc.method.annotation;

import org.springframework.stereotype.Controller;

@Controller
public class NotAnnotatedTestController 
{
	public String handleNotAnnotatedMethod() {
		return null;
	}
	
	@HttpResponseHeader(name="X-Key", value="X-Value-Method")
	public String handleExtraAnnotatedMethod() {
		return null;
	}

	@HttpResponseHeaders(
		{
			@HttpResponseHeader(name="X-Key-One", value="X-Value-One-Method"),
			@HttpResponseHeader(name="X-Key-Two", value="X-Value-Two-Method")
		}
	)
	public String handleMultipleExtraAnnotatedMethod() {
		return null;
	}
	
	@HttpResponseHeaders(
			{
			}
		)
	public String handleWithEmptyMultipleExtraAnnotatedMethod() {
		return null;	
	}
	
}
