package com.example.rest.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
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
}
