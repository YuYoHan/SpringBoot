package com.example.web_sty.controller;

import com.example.web_sty.dto.SendDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AjaxController {

    @GetMapping("/ajax")
    public String ajax() {
        return "/ajax/ajax";
    }

    @PostMapping("/send")
    public String ajaxHome(Model model, SendDTO sendDTO) {
        model.addAttribute("msg", sendDTO.getResult());
        return "ajax :: #resultDiv";
    }
}
