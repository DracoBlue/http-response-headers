package net.dracoblue.spring.web.mvc.method.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpResponseHeaderHandlerInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
	
	private SpelExpressionParser parser;
	
	public HttpResponseHeaderHandlerInterceptor() {
		super();
		this.parser = new SpelExpressionParser(); 
	}
	
	protected final void assignHttpResponseHeaders(
			final HttpServletRequest request,
			final HttpServletResponse response, 
			final Object handler) {
		
		final List<HttpResponseHeader> httpResponseHeaders = this.getHttpResponseHeader(request, response, handler);
		
		for (HttpResponseHeader httpResponseHeader : httpResponseHeaders) {
			String name = httpResponseHeader.name();
			String value = httpResponseHeader.value();
			
			
			if (httpResponseHeader.nameExpression()) {
				name = (String) parser.parseExpression(name).getValue();
			}
			
			if (httpResponseHeader.valueExpression()) {
				value = (String) parser.parseExpression(value).getValue();
			}
			
			response.setHeader(name, value);
		}
	}
	
	protected final List<HttpResponseHeader> getHttpResponseHeader(
			final HttpServletRequest request,
			final HttpServletResponse response, 
			final Object handler) {
		
		if (handler == null || !(handler instanceof HandlerMethod)) {
			return null;
		}

		final HandlerMethod handlerMethod = (HandlerMethod) handler;

		List<HttpResponseHeader> httpResponseHeadersList = new ArrayList<HttpResponseHeader>();

		HttpResponseHeaders beanHttpResponseHeaders = handlerMethod.getBeanType().getAnnotation(HttpResponseHeaders.class);
		if (beanHttpResponseHeaders != null) {
			httpResponseHeadersList.addAll(Arrays.asList(beanHttpResponseHeaders.value()));
		}

		HttpResponseHeader beanHttpResponseHeader = handlerMethod.getBeanType().getAnnotation(HttpResponseHeader.class);
		if (beanHttpResponseHeader != null) {
			httpResponseHeadersList.add(beanHttpResponseHeader);
		}

		HttpResponseHeaders methodHttpResponseHeaders = handlerMethod.getMethodAnnotation(HttpResponseHeaders.class);
		if (methodHttpResponseHeaders != null) {
			httpResponseHeadersList.addAll(Arrays.asList(methodHttpResponseHeaders.value()));
		}
		
		HttpResponseHeader methodHttpResponseHeader = handlerMethod.getMethodAnnotation(HttpResponseHeader.class);
		
		if (methodHttpResponseHeader != null) {
			httpResponseHeadersList.add(methodHttpResponseHeader);
		}

		return httpResponseHeadersList;
	}
	
	@Override
	public final boolean preHandle(
			final HttpServletRequest request,
			final HttpServletResponse response, 
			final Object handler) throws Exception {
		
		this.assignHttpResponseHeaders(request, response, handler);
		
		return super.preHandle(request, response, handler);
	}

}
