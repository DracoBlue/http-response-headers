# net.dracoblue.spring:http-response-headers

* Latest Release: [![GitHub version](https://badge.fury.io/gh/DracoBlue%2Fhttp-response-headers.png)](https://github.com/DracoBlue/http-response-headers/releases)
* Build Status: [![Build Status](https://secure.travis-ci.org/DracoBlue/http-response-headers.png?branch=master)](http://travis-ci.org/DracoBlue/http-response-headers)

This small java library is used with spring to use Annotations for HTTP Response Headers.

## Usage

You can specifiy `@HttpResponseHeader` and `@HttpResponseHeaders` for methods and classes. If an annotation
is visible on method level it will ignore the class level one. If the class annotation provides the same
header key like a method annotation, the method annotation will override the value of the class annotation.

### Methods

``` java
/* One Header */
@HttpResponseHeader(name="X-Key", value="X-Value-Method")
public String handleExtraAnnotatedMethod() {
    return null;
}

/* Multiple headers */
@HttpResponseHeaders(
    {
        @HttpResponseHeader(name="X-Key-One", value="X-Value-One-Method"),
        @HttpResponseHeader(name="X-Key-Two", value="X-Value-Two-Method")
    }
)
public String handleMultipleExtraAnnotatedMethod() {
    return null;
}
```

### Classes

``` java
@HttpResponseHeader(name="X-Key-For-Response-Header", value="X-Value-For-Response-Header-Class")
@HttpResponseHeaders(
    {
        @HttpResponseHeader(name="X-Key-For-Response-Headers-One", value="X-Value-For-Response-Headers-One-Class"),
        @HttpResponseHeader(name="X-Key-For-Response-Headers-Two", value="X-Value-For-Response-Headers-Two-Class")
    }
)
@Controller
public class FullyAnnotatedTestController  {
    /* ... */
}
```

### Spring Expression Language

It's also possible, to use [SpEL](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/expressions.html) for 
the `name` and `value` property.

To enable parsing for the `value` property, enable it with `valueExpression=true`.

For example:
``` java
@HttpResponseHeader(name="Cache-Control", value="#{'max-age=' + (60*5)}", valueExpression=true)
```
will result in:
``` text
Cache-Control: max-age=300
```

If you use that to read properties (like `java.version`):

``` java
@HttpResponseHeader(name="X-Java-Version", value="#{environment.getProperty('java.version')}", valueExpression=true)
```

will result in:

``` text
X-Java-Version: 1.8.0_25
```

## Installation

Replace VERSION with [![GitHub version](https://badge.fury.io/gh/DracoBlue%2Fhttp-response-headers.png)](https://github.com/DracoBlue/http-response-headers/releases) or 0.1.0-SNAPSHOT.

Maven:

``` xml
<dependency>
    <groupId>net.dracoblue.spring</groupId>
    <artifactId>http-response-headers</artifactId>
    <version>VERSION</version>
</dependency>
```

Gradle/Grails:

``` text
compile 'net.dracoblue.spring:http-response-headers:VERSION'
```

Add this to your SpringBootApplication:

``` java
@Autowired
HttpResponseHeaderHandlerInterceptor httpResponsHeaderHandlerInterceptor;

@Bean
public WebMvcConfigurer contentNegotiatorConfigurer()
{
    return new WebMvcConfigurerAdapter()
    {
       @Override
       public void addInterceptors(InterceptorRegistry registry)
       {
           registry.addInterceptor(httpResponsHeaderHandlerInterceptor);
       }                
    };
}
```

## Other Projects / Interesting Links

* [spring-mvc-cache-control by foo4u](https://github.com/foo4u/spring-mvc-cache-control)

## License

This work is copyright by DracoBlue (<http://dracoblue.net>) and licensed under the terms of MIT License.
