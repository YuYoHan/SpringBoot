package com.example.shopping_.controller;

import com.example.shopping_.DTO.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
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

    @GetMapping("ex03")
    public String ex03(Model model) {
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ItemDTO item = new ItemDTO();
            item.setItemDetail("상품 상세 설명" + i);
            item.setItemNm("상품 " + i);
            item.setPrice(10000 * i);
            item.setRegTime(LocalDateTime.now());

            itemDTOList.add(item);
        }
        model.addAttribute("itemList", itemDTOList);
        return "/ex03";
    }

    @GetMapping("ex04")
    public String ex04(Model model) {
        List<ItemDTO> itemDTOList2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ItemDTO item = new ItemDTO();
            item.setItemDetail("상품 상세 설명" + i);
            item.setItemNm("상품 " + i);
            item.setPrice(10000 * i);
            item.setRegTime(LocalDateTime.now());

            itemDTOList2.add(item);
        }
        model.addAttribute("itemList", itemDTOList2);
        return "/ex04";
    }

    @GetMapping("ex05")
    public String ex05(Model model) {
        List<ItemDTO> itemDTOList3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ItemDTO item = new ItemDTO();
            item.setItemDetail("상품 상세 설명" + i);
            item.setItemNm("상품 " + i);
            item.setPrice(10000 * i);
            item.setRegTime(LocalDateTime.now());

            itemDTOList3.add(item);
        }
        model.addAttribute("itemList", itemDTOList3);
        return "/ex05";
    }

    @GetMapping("ex06")
    public String ex06() {
        return "/ex06";
    }

    @GetMapping("ex07")
    public String ex07(String param1, String param2, Model model) {
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "/ex07";
    }

    @GetMapping("/ex08")
    public String ex08() {
        return "ex08";
    }

}
