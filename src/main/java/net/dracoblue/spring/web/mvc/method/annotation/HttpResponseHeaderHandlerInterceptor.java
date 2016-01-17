package net.dracoblue.spring.web.mvc.method.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpResponseHeaderHandlerInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
	public HttpResponseHeaderHandlerInterceptor() {
		super();
	}
	
	protected final void assignHttpResponseHeaders(
			final HttpServletRequest request,
			final HttpServletResponse response, 
			final Object handler) {
		
		final List<HttpResponseHeader> httpResponseHeaders = this.getHttpResponseHeader(request, response, handler);
		
		for (HttpResponseHeader httpResponseHeader : httpResponseHeaders) {
			response.setHeader(httpResponseHeader.name(), httpResponseHeader.value());
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

		HttpResponseHeaders methodHttpResponseHeaders = handlerMethod.getMethodAnnotation(HttpResponseHeaders.class);
		HttpResponseHeader methodHttpResponseHeader = handlerMethod.getMethodAnnotation(HttpResponseHeader.class);

		/*
		 * Use @HttpResponseHeaders class annotation, if there is no method annotation
		 */
		if (methodHttpResponseHeaders == null) {
			HttpResponseHeaders beanHttpResponseHeaders = handlerMethod.getBeanType().getAnnotation(HttpResponseHeaders.class);
			if (beanHttpResponseHeaders != null) {
				httpResponseHeadersList.addAll(Arrays.asList(beanHttpResponseHeaders.value()));
			}
		}

		/*
		 * Use @HttpResponseHeader class annotation, if there is no method annotation
		 */
		if (methodHttpResponseHeader == null) {
			HttpResponseHeader beanHttpResponseHeader = handlerMethod.getBeanType().getAnnotation(HttpResponseHeader.class);
			if (beanHttpResponseHeader != null) {
				httpResponseHeadersList.add(beanHttpResponseHeader);
			}
		}
		
		/*
		 * Override with @HttpResponseHeaders method annotation
		 */
		if (methodHttpResponseHeaders != null) {
			httpResponseHeadersList.addAll(Arrays.asList(methodHttpResponseHeaders.value()));
		}
		
		/*
		 * Override with @HttpResponseHeader method annotation
		 */
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
