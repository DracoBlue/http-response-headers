package net.dracoblue.spring.web.mvc.method.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = { "net.dracoblue" })
public class HttpResponseHeadersTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpResponseHeadersTestApplication.class, args);
	}

	@Autowired
	HttpResponseHeaderHandlerInterceptor httpResponsHeaderHandlerInterceptor;

	@Bean
	public WebMvcConfigurer contentNegotiatorConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(httpResponsHeaderHandlerInterceptor);
			}
		};
	}

}
