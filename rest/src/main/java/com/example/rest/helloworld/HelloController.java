package com.example.rest.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    // GET
    // /hello-world
    // 예전 : @RequestMapping → 요즘 : @GetMapping
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    /*
        return
    *       {
    *           "message": "Hello World"
    *       }
    * */
    @GetMapping("/hello-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    /*
    *   return
    *       {
    *          "message": "Hello World, 채원"
    *       }
    * */
    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false)Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
