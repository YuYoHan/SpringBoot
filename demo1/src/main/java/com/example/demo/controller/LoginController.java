package com.example.demo.controller;

import com.example.demo.domain.LoginForm;
import com.example.demo.domain.MemberDTO;
import com.example.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Validated LoginForm loginForm,
                        // BindingResult는 검증 오류가 발생할 경우 오류 내용을 보관하는 스프링 프레임워크에서 제공하는 객체
                        // 또한, BindingResult가 있으면 @ModelAttribute에 데이터 바인딩 시 오류가 발생해도 오류 정보를
                        // FieldError 개체를 BindingResult가 담은 뒤 컨트롤러가 호출됩니다.
                        // 여기서 주의할 점은 BindingResult 객체의 파라미터 위치는
                        // 반드시 @ModelAttribute 어노테이션이 붙은 객체 다음에 위치해야 한다는 점입니다.
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirctURL) {
        if(bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        MemberDTO loginMember = loginService.Login(loginForm.getLoginId(),loginForm.getPassword());

        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다. ");
            return "login/loginForm";
        }
        // 로그인 성공 처리
        return "redirect: " + redirctURL;
    }
}
