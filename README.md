# net.dracoblue.spring:response-headers

* Latest Release: [![GitHub version](https://badge.fury.io/gh/DracoBlue%2Fresponse-headers.png)](https://github.com/DracoBlue/response-headers/releases)
* Build Status: [![Build Status](https://secure.travis-ci.org/DracoBlue/response-headers.png?branch=master)](http://travis-ci.org/DracoBlue/response-headers)

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

## Installation

TBD

## Other Projects / Interesting Links

* [spring-mvc-cache-control by foo4u](https://github.com/foo4u/spring-mvc-cache-control)

## License

This work is copyright by DracoBlue (<http://dracoblue.net>) and licensed under the terms of MIT License.
