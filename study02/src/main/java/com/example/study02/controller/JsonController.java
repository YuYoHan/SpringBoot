package com.example.study02.controller;

import com.example.study02.TestApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonController {

    @GetMapping("Test")
    @ResponseBody
    public TestApi testApi() {
        TestApi testApi = new TestApi();
        testApi.setId("testId");
        testApi.setPassword("testPw");
        return testApi;
    }
}
