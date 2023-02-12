package com.example.study02.controller;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GsonController {

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        JsonObject obj = new JsonObject();

        obj.addProperty("title", "테스트");
        obj.addProperty("content", "테스트 내용");

        JsonObject data = new JsonObject();
        data.addProperty("time", "12:00");
        obj.add("data", data);

        return obj.toString();
    }
}