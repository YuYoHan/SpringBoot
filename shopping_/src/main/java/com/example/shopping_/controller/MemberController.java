package com.example.shopping_.controller;

import com.example.shopping_.DTO.MemberFormDTO;
import com.example.shopping_.entity.Member;
import com.example.shopping_.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("new")
    private String memberForm(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "member/memberForm";
    }

    @PostMapping("/new")
    // 검증하려는 객체의 앞에 @Valid를 선언하고 파라미터로 bindingResult 객체를 추가합니다.
    // 검사 후 bindingResult에 담아줍니다. bindingResult.hasErrors()를 호출하여 에러가
    // 있다면 회원 가입 페이지로 이동합니다.
    public String memberForm(@Valid MemberFormDTO memberFormDTO,
                             BindingResult bindingResult,
                             Model model) {
        if(bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDTO, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            // 회원 가입 시 중복 회원 가입 예외가 발생하면 에러 메시지를 뷰로 전달
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginMember() {
        return "member/memberLoginForm";
    }
    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 or 비밀번호를 확인해주세요");
        return "member/memberLoginForm";
    }
}
