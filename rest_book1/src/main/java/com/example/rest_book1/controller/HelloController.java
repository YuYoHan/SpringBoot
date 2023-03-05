package com.example.rest_book1.controller;

import com.example.rest_book1.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어준다.
// 메소드마다 @ResponseBody를 붙여도 되지만
// 클래스에 @RestController를 쓰면 한번에 해결할 수 있다.
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDTO helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDTO(name, amount);
    }
}
