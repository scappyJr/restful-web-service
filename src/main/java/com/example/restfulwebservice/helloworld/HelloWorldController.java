package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    /**
     * Get /hello-world (endpoint)
     * 이전 방식 -> @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
     */
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    /**
     * RestController 이용 시 별도로 ResponseBody 값을 반환하지 않아도
     * Class, Collection 등의 객체를 JSON 형태로 반환한다.
     * (XML 형태로 반환하려는 경우 별도의 라이브러리 추가 필요)
     */
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world!");
    }

    // path variable 을 이용하여 가변 uri 처리
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello world %s!", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
