package com.example.shopping_.controller;

import com.example.shopping_.DTO.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/*")
public class ThymeleafController {
    @GetMapping("/thymeleafEx01")
    public String thymeleafExample01(Model model) {
        model.addAttribute("data", "타임리프 예제입니다.");
        return "/thymeleafEx01";
    }

    @GetMapping("ex02")
    public String thymeleafEx02(Model model) {
        ItemDTO item = new ItemDTO();
        item.setItemDetail("상품 상세 설명");
        item.setItemNm("상품 1");
        item.setPrice(10000);
        item.setRegTime(LocalDateTime.now());

        model.addAttribute("item", item);
        return "/ex02";
    }
}
