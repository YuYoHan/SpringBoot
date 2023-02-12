package com.example.study02.controller;

import com.google.gson.JsonArray;
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

    @ResponseBody
    @RequestMapping("/test2")
    public String test2() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", "Gson Test Tile");
        jsonObject.addProperty("content", "Gson Test Content");

        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < 5; i++) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("data", i);
            jsonArray.add(jsonObject2);
        }
        jsonObject.add("testData", jsonArray);
        return jsonObject.toString();
    }
}
