package net.dracoblue.spring.web.mvc.method.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpressionController
{
	@HttpResponseHeader(name="X-Key", value="#{'X-Value-Method'}", valueExpression=true)
	@RequestMapping("/handleValueExpression")
	public String handleValueExpression() {
		return null;
	}

	@HttpResponseHeaders(
		{
			@HttpResponseHeader(name="X-Key-One", value="#{'X-Value-One-Method'}", valueExpression=true),
			@HttpResponseHeader(name="X-Key-Two", value="#{'X-Value-Two-Method'}", valueExpression=true)
		}
	)
	@RequestMapping("/handleMultipleValueExpressions")
	public String handleMultipleValueExpressions() {
		return null;
	}
	
	@HttpResponseHeader(name="#{'X-Key'}", value="X-Value-Method", nameExpression=true)
	@RequestMapping("/handleNameExpression")
	public String handleNameExpression() {
		return null;
	}

	@HttpResponseHeaders(
		{
			@HttpResponseHeader(name="#{'X-Key-One'}", value="X-Value-One-Method", nameExpression=true),
			@HttpResponseHeader(name="#{'X-Key-Two'}", value="X-Value-Two-Method", nameExpression=true)
		}
	)
	@RequestMapping("/handleMultipleNameExpressions")
	public String handleMultipleNameExpressions() {
		return null;
	}	

	@HttpResponseHeader(name="#{'X-Key'}", value="#{'X-Value-Method'}", nameExpression=true, valueExpression=true)
	@RequestMapping("/handleNameAndValueExpression")
	public String handleNameAndValueExpression() {
		return null;
	}

	@HttpResponseHeaders(
		{
			@HttpResponseHeader(name="#{'X-Key-One'}", value="#{'X-Value-One-Method'}", nameExpression=true, valueExpression=true),
			@HttpResponseHeader(name="#{'X-Key-Two'}", value="#{'X-Value-Two-Method'}", nameExpression=true, valueExpression=true)
		}
	)
	@RequestMapping("/handleMultipleNameAndValueExpressions")
	public String handleMultipleNameAndValueExpressions() {
		return null;
	}

	@HttpResponseHeader(name="Cache-Control", value="max-age=#{5 * 60}", valueExpression=true)
	@RequestMapping("/handleCalculation")
	public String handleCalculation() {
		return "error";
	}

	@HttpResponseHeader(name="X-Java-Version", value="#{environment.getProperty('java.version')}", valueExpression=true)
	@RequestMapping("/handlePropertyExpressions")
	public String handlePropertyExpressions() {
		return null;
	}
}
