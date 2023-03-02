package com.example.shopping_.controller;

import com.example.shopping_.DTO.MemberFormDTO;
import com.example.shopping_.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    private String memberForm(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "member/memberForm";
    }
}
