package net.dracoblue.spring.web.mvc.method.annotation;

import org.springframework.stereotype.Controller;

@HttpResponseHeader(name="X-Key-For-Response-Header", value="X-Value-For-Response-Header-Class")
@HttpResponseHeaders(
	{
		@HttpResponseHeader(name="X-Key-For-Response-Headers-One", value="X-Value-For-Response-Headers-One-Class"),
		@HttpResponseHeader(name="X-Key-For-Response-Headers-Two", value="X-Value-For-Response-Headers-Two-Class")
	}
)
@Controller
public class FullyAnnotatedTestController 
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
	
	@HttpResponseHeader(name="X-Key-For-Response-Header", value="X-Value-Method")
	public String handleOverrideClassAnnotationMethod() {
		return null;
	}

	@HttpResponseHeaders(
			{
				@HttpResponseHeader(name="X-Key-For-Response-Header", value="X-Value-Method")
			}
		)
	public String handleOverrideClassAnnotationWithOneHeaderInMultipleHeadersMethod() {
		return null;
	}

	@HttpResponseHeader(name="X-Key-For-Response-Headers-One", value="X-Value-For-Response-Headers-One-Method")
	public String handleOverrideClassAnnotationWithSingleHeaderMethod() {
		return null;
	}

}
