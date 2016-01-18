package net.dracoblue.spring.web.mvc.method.annotation;

import org.springframework.stereotype.Controller;

@Controller
public class ExpressionController 
{
	@HttpResponseHeader(name="X-Key", value="'X-Value-Method'", valueExpression=true)
	public String handleValueExpression() {
		return null;
	}

	@HttpResponseHeaders(
		{
			@HttpResponseHeader(name="X-Key-One", value="'X-Value-One-Method'", valueExpression=true),
			@HttpResponseHeader(name="X-Key-Two", value="'X-Value-Two-Method'", valueExpression=true)
		}
	)
	public String handleMultipleValueExpressions() {
		return null;
	}
	
	@HttpResponseHeader(name="'X-Key'", value="X-Value-Method", nameExpression=true)
	public String handleNameExpression() {
		return null;
	}

	@HttpResponseHeaders(
		{
			@HttpResponseHeader(name="'X-Key-One'", value="X-Value-One-Method", nameExpression=true),
			@HttpResponseHeader(name="'X-Key-Two'", value="X-Value-Two-Method", nameExpression=true)
		}
	)
	public String handleMultipleNameExpressions() {
		return null;
	}	

	@HttpResponseHeader(name="'X-Key'", value="'X-Value-Method'", nameExpression=true, valueExpression=true)
	public String handleNameAndValueExpression() {
		return null;
	}

	@HttpResponseHeaders(
		{
			@HttpResponseHeader(name="'X-Key-One'", value="'X-Value-One-Method'", nameExpression=true, valueExpression=true),
			@HttpResponseHeader(name="'X-Key-Two'", value="'X-Value-Two-Method'", nameExpression=true, valueExpression=true)
		}
	)
	public String handleMultipleNameAndValueExpressions() {
		return null;
	}

	@HttpResponseHeader(name="Cache-Control", value="'max-age=' + (60*5)", valueExpression=true)
	public String handleCalculation() {
		return null;
	}
}
